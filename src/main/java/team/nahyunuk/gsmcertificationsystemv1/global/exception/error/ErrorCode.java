package team.nahyunuk.gsmcertificationsystemv1.global.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // jwt
    EXPIRED_TOKEN(401, "토큰이 만료되었습니다"),
    INVALID_TOKEN(401, "유효하지 않은 토큰입니다."),
    EXPIRED_REFRESH_TOKEN(401, "만료된 리프레쉬 토큰입니다."),

    // member
    NOT_FOUND_MEMBER(404, "등록된 회원을 찾을 수 없습니다");

    private final HttpStatus httpStatus;
    private final String message;
}
