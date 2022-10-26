package com.example.gururu_be.controller.member;

import com.example.gururu_be.domain.dto.ResResultDto;
import com.example.gururu_be.domain.dto.member.ReqMemberInfoDto;
import com.example.gururu_be.service.member.MemberService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/v1.0/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
     *  M-1-4 회정 정보 수정
     */
    @ApiOperation(value="M-1-4 회정 정보 수정", notes="시스템에 등록된 현재 회원 정보를 수정한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @RequestMapping(value="/", method={RequestMethod.PUT})
    @PutMapping("/")
    public ResponseEntity<ResResultDto> modifyMember(@RequestBody ReqMemberInfoDto reqMemberInfoDto) {

        memberService.updateMemberInfo(reqMemberInfoDto);

        // 반환값 : 결과 메시지, 상태값(200)
        return ResponseEntity.ok(new ResResultDto("회원 정보 수정 성공"));
    }



}
