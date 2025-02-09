package team.nahyunuk.gsmcertificationsystem.v1.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.error.ErrorCode;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {
    private final ErrorCode errorCode;
}
