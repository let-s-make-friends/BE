package team.nahyunuk.gsmcertificationsystem.v1.global.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // jwt
    EXPIRED_TOKEN(401, "만료된 토큰입니다."),
    INVALID_TOKEN(401, "유효하지 않은 토큰입니다."),
    EXPIRED_REFRESH_TOKEN(401, "만료된 리프레쉬 토큰입니다."),
    NOT_FOUND_REFRESH_TOKEN(404, "존재하지 않는 리프레시 토큰입니다."),
    UNAUTHORIZED(401, "인증되지 않은 사용자입니다."),
    TOKEN_MALFORMED(400, "형식이 잘못된 토큰입니다."),
    MISSING_TOKEN(400, "토큰이 누락되었습니다."),
    FAILED_JWT_AUTH(401, "jwt 인증에 실패했습니다."),
    MISSING_REFRESH_TOKEN(400, "헤더에 리프레시 토큰을 추가해주세요."),

    // user
    USER_NOT_FOUND(404, "등록된 회원을 찾을 수 없습니다."),
    USER_ALREADY_EXISTS(409, "이미 가입된 회원입니다."),
    EXPIRED_OR_INVALID_CODE(400, "만료되었거나 유효하지 않은 인증 코드입니다."),
    INVALID_CODE(400, "잘못된 인증 코드입니다."),
    EMAIL_NOT_VERIFIED(401, "이메일 인증이 완료되지 않았습니다."),
    INVALID_PASSWORD(401, "비밀번호가 일치하지 않습니다."),
    EMAIL_NOT_PENDING(400, "이메일 인증 대기 상태가 아닙니다."),
    INVALID_EMAIL_FORMAT(400, "잘못된 이메일 형식입니다."),

    // image
    FILE_EXTENSION_INVALID(400, "파일 확장자가 유효하지 않습니다."),

    // server
    EMAIL_SEND_FAILID(500, "이메일 발송에 실패했습니다."),
    INTERNAL_SERVER_ERROR(500, "서버 오류");


    private final int status;
    private final String message;
}
