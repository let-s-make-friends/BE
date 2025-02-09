package team.nahyunuk.gsmcertificationsystem.v1.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.error.ErrorResponse;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        log.warn("ExpectedException : {} ", e.getMessage());
        log.trace("ExpectedException Details : ", e);
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }
}
