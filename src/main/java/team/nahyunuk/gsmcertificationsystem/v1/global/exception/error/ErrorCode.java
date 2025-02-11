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

    // user
    USER_NOT_FOUND(404, "등록된 회원을 찾을 수 없습니다."),
    USER_ALREADY_EXISTS(409, "이미 가입된 회원입니다."),
    EXPIRED_OR_INVALID_CODE(400, "만료되었거나 유효하지 않은 인증 코드입니다."),
    INVALID_CODE(400, "잘못된 인증 코드입니다."),
    EMAIL_NOT_VERIFIED(401, "이메일 인증이 완료되지 않았습니다."),

    // server
    INTERNAL_SERVER_ERROR(500, "서버 오류");

    private final int status;
    private final String message;
}
