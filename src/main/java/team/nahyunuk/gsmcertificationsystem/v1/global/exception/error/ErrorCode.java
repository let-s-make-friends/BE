package team.nahyunuk.gsmcertificationsystem.v1.global.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // jwt
    EXPIRED_TOKEN(401, "토큰이 만료되었습니다"),
    INVALID_TOKEN(401, "유효하지 않은 토큰입니다."),
    EXPIRED_REFRESH_TOKEN(401, "만료된 리프레쉬 토큰입니다."),
    NOT_FOUND_REFRESH_TOKEN(404, "존재하지 않는 리프레시 토큰입니다"),

    // member
    MEMBER_NOT_FOUND(404, "등록된 회원을 찾을 수 없습니다"),

    // server
    INTERNAL_SERVER_ERROR(500, "서버 오류");

    private final int status;
    private final String message;
}
