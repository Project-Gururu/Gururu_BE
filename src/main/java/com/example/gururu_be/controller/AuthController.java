package com.example.gururu_be.controller;

import com.example.gururu_be.domain.dto.JwtTokenDto;
import com.example.gururu_be.domain.dto.ResResultDto;
import com.example.gururu_be.domain.dto.member.ResMemberInfoDto;
import com.example.gururu_be.domain.dto.social.SocialUserInfoDto;
import com.example.gururu_be.domain.repository.member.MemberRepository;
import com.example.gururu_be.service.AuthKakaoService;
import com.example.gururu_be.service.AuthService;
import com.example.gururu_be.util.exception.ErrorCode;
import com.example.gururu_be.util.exception.RequestException;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;
    private final AuthKakaoService authKakaoService;
    private final MemberRepository memberRepository;

    /**
     * <pre>
     *     ==소셜로그인 인증 절차==
     *     1. 프론트엔드에서 소셜로그인 시작 후 인증서버로부터 authorization code 발급받는다.
     *     2. authorization code 를 현재 메소드로 백엔드가 전달받는다.
     *     3. authorization code 를 이용하여 인증서버에게 사용자 인증 토큰을 요청/발급 받는다.
     *     4. 인증 토큰을 이용하여 소셜 API 서버에게 사용자 정보를 전달 받는다.
     *     5. 백엔드에서 사용자 정보를 토대로 로그인(회원가입)처리를 진행한다.
     *     6. 프론트엔드에게 자체 인증 토큰(JWT)를 전달하여 인증 완료를 알린다.
     * </pre>
     */
    @ApiOperation(value="M1-1 로그인", notes="시스템에 등록된 회원을 로그인한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @RequestMapping(value="/{social}", method={RequestMethod.POST})
    @PostMapping("/{social}")
    public ResponseEntity<ResMemberInfoDto> login(
            @PathVariable("social") String socialPath, @RequestParam(name = "code") String code,
            HttpServletResponse response) throws JsonProcessingException {

        /**
         * 2~4번 과정 수행
         * *authorization code를 이용하여, 사용자 정보(socialUserInfoDto)를 받아온다.
         */
        SocialUserInfoDto socialUserInfoDto = null;
        switch (socialPath) {
            case "kakao":
                socialUserInfoDto = authKakaoService.kakao(code);
                break;
        }
        if (socialUserInfoDto == null) {
            throw new RequestException(ErrorCode.COMMON_BAD_REQUEST_400);
        }

        /**
         * 5번 수행
         * socialUserInfoDto 를 이용하여 자체 서비스의 사용자 인증을 처리한다.
         */
        String loginId = authService.login(socialUserInfoDto);

        JwtTokenDto jwtTokenDto = authService.getJwtTokenDto(loginId);

        setJwtCookie(response, jwtTokenDto);

        ResMemberInfoDto resMemberInfoDto = authService.getMbId(loginId);

        /**
         * 6번 수행 클라이언트로 보낼 mbid정보를 보낸다
         */

        return ResponseEntity.ok(resMemberInfoDto);
    }

    @ApiOperation(value="M1-2 리프레쉬토큰 갱신", notes="시스템에 등록된 회원의 리프레쉬토큰 갱신한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @RequestMapping(value="/reissue", method={RequestMethod.POST})
    @PostMapping("/reissue")
    public ResponseEntity<ResResultDto> reissue(HttpServletRequest request, HttpServletResponse response) {

        Cookie[] cookies = request.getCookies();
        String refreshToken = null;

        if (cookies == null) {
            throw new RequestException(ErrorCode.JWT_NOT_FOUND_404);
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("refreshToken")) {
                refreshToken = cookie.getValue();
            }
        }

        if (refreshToken == null) {
            throw new RequestException(ErrorCode.JWT_NOT_FOUND_404);
        }

        JwtTokenDto jwtTokenDto = authService.reissue(JwtTokenDto.builder()
                .refreshToken(refreshToken)
                .build());

        setJwtCookie(response, jwtTokenDto);

        return ResponseEntity.ok(new ResResultDto("JWT 갱신 완료"));
    }

    @DeleteMapping("/logout")
    public ResponseEntity<ResResultDto> logout(HttpServletRequest request, HttpServletResponse response) {

        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) {
            return ResponseEntity.ok(new ResResultDto("로그아웃 성공"));
        }

        for (Cookie cookie : cookies) {
            log.info("cookie name: '{}'", cookie.getName());
            log.info("cookie value: '{}'", cookie.getValue());
            log.info("cookie domain: '{}'", cookie.getDomain());
        }

        ResponseCookie responseCookie = ResponseCookie.from("accessToken", "")
                .domain("localhost")
                .httpOnly(false)
                .maxAge(0)
                .sameSite("None")
                .secure(true)
                .path("/").build();

        response.addHeader(HttpHeaders.SET_COOKIE, responseCookie.toString());

        responseCookie = ResponseCookie.from("refreshToken", "")
                .domain("localhost")
                .httpOnly(false)
                .maxAge(0)
                .sameSite("None")
                .secure(true)
                .path("/").build();

        response.addHeader(HttpHeaders.SET_COOKIE, responseCookie.toString());

        String loginId = SecurityContextHolder.getContext().getAuthentication().getName();
        authService.logout(loginId);

        return ResponseEntity.ok(new ResResultDto("로그아웃 성공"));
    }

    private void setJwtCookie(HttpServletResponse response, JwtTokenDto jwtTokenDto) {

        ResponseCookie responseCookie = ResponseCookie.from("accessToken", jwtTokenDto.getAccessToken())
                .domain("localhost")
                .httpOnly(false)
                .maxAge(60 * 30)
                .sameSite("None")
                .secure(true)
                .path("/").build();

        response.addHeader(HttpHeaders.SET_COOKIE, responseCookie.toString());

        responseCookie = ResponseCookie.from("refreshToken", jwtTokenDto.getRefreshToken())
                .domain("localhost")
                .httpOnly(false)
                .maxAge(60 * 60 * 24)
                .sameSite("None")
                .secure(true)
                .path("/").build();

        response.addHeader(HttpHeaders.SET_COOKIE, responseCookie.toString());
    }
}
