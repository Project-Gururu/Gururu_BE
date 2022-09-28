package com.example.gururu_be.util.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 참고사이트
 * <a href="https://github.com/HangHae99ProjectTeam10/SharePod-Server/blob/main/src/main/java/com/spring/sharepod/exception/CommonError/ErrorCode.java">https://github.com/HangHae99ProjectTeam10/SharePod-Server/blob/main/src/main/java/com/spring/sharepod/exception/CommonError/ErrorCode.java</a>
 * <br>
 * <pre>
 * 4xx 클라이언트 오류
 * 400 Bad Request : 잘못된 요청.
 * 401 Unauthorized : 인증이 필요함.
 * 403 Forbidden : 접근 권한 없음.
 * 404 Not Found : Resource 없음.
 * 405 Methods Not Allowed : 유효하지 않은 요청
 * 409 Conflict : 리소스 충돌(중복)
 *
 * 5xx 서버 오류
 * 500 Internal Server Error : 서버 오류 발생
 * 503 Service Unavailable : 서비스 사용 불가
 * </pre>
 */

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // 공통
    COMMON_BAD_REQUEST_400(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    COMMON_INTERNAL_ERROR_500(HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 오류가 발생하였습니다."),

    // JWT 관련
    JWT_BAD_TOKEN_400(HttpStatus.BAD_REQUEST, "잘못된 JWT 토큰입니다."),
    JWT_UNAUTHORIZED_401(HttpStatus.UNAUTHORIZED, "만료된 JWT 토큰입니다."),
    JWT_NOT_FOUND_404(HttpStatus.NOT_FOUND, "유효한 JWT 토큰이 없습니다."),
    JWT_NOT_ALLOWED_405(HttpStatus.METHOD_NOT_ALLOWED, "지원되지 않는 JWT 토큰입니다."),

    // Member 관련
    MEMBER_LOGINID_NOT_FOUND_404(HttpStatus.NOT_FOUND, "회원 정보를 찾을 수 없습니다."),
    MEMBER_LOGINID_DUPLICATION_409(HttpStatus.CONFLICT, "이미 가입된 이메일입니다."),

    // Store 관련
    STORE_NOT_FOUND_404(HttpStatus.NOT_FOUND, "요청한 가게 ID가 없습니다."),
    STORE_DUPLICATION_409(HttpStatus.CONFLICT, "이미 등록된 가게 ID 입니다."),
    STORE_DELETE_409(HttpStatus.CONFLICT, "삭제된 가게 ID 입니다."),

    // Beautician 관련
    BEAUTICIAN_NOT_FOUND_404(HttpStatus.NOT_FOUND, "요청한 스타일리스트 ID가 없습니다."),
    BEAUTICIAN_DUPLICATION_409(HttpStatus.CONFLICT, "이미 등록된 스타일리스트 ID 입니다."),
    BEAUTICIAN_DELETE_409(HttpStatus.CONFLICT, "삭제된 스타일리스트 ID 입니다."),

    // MemberLocalization 관련
    MEMBER_LOCALIZATION_NOT_FOUND_404(HttpStatus.NOT_FOUND, "요청한 멤버위치 ID가 없습니다."),
    MEMBER_LOCALIZATION_DUPLICATION_409(HttpStatus.CONFLICT, "이미 등록된 멤버위치 ID 입니다."),
    MEMBER_LOCALIZATION_DELETE_409(HttpStatus.CONFLICT, "삭제된 멤버위치 ID 입니다."),


    // Role 관련
    NO_PERMISSION_TO_WRITE_NOTICE_400(HttpStatus.FORBIDDEN, "공지 사항 작성 권한이 없습니다."),
    NO_PERMISSION_TO_MODIFY_NOTICE_400(HttpStatus.FORBIDDEN, "공지 사항 수정 권한이 없습니다."),
    NO_PERMISSION_TO_DELETE_NOTICE_400(HttpStatus.FORBIDDEN, "공지 사항 삭제 권한이 없습니다."),


    /**
     * 해당 주석 위로 enum 코드 추가 바랍니다.
     * 코드 추가시 간편하게 진행하기 위해 생성한 미사용 코드입니다. 사용하지마세요.
     * 생성이유 : enum 마지막 요소에 ; 을 입력해야하기에, 끝부분에 추가하게 될 경우 ; 을 재입력해야함
     */
    DO_NOT_USED(null, null);


    private final HttpStatus httpStatus;
    private final String message;
}
