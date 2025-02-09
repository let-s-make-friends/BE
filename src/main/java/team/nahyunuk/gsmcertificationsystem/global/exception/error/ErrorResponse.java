package team.nahyunuk.gsmcertificationsystemv1.global.exception.error;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
@Builder
public class ErrorResponse {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final int status;
    private final String error;
    private final String code;
    private final String message;

    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorCode errorCode) {
        return ResponseEntity
                .status(HttpStatus.valueOf(errorCode.getStatus()))
                .body(ErrorResponse.builder()
                        .status(errorCode.getStatus())
                        .error(HttpStatus.valueOf(errorCode.getStatus()).name())
                        .code(errorCode.name())
                        .message(errorCode.getMessage())
                        .build());
    }
}
