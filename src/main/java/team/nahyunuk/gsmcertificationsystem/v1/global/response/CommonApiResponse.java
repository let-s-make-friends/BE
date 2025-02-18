package team.nahyunuk.gsmcertificationsystem.v1.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommonApiResponse<T> {
    private HttpStatus status;
    private int code;
    private String message;
    private T data;

    public static CommonApiResponse<?> success(String message) {
        return new CommonApiResponse<>(HttpStatus.OK, HttpStatus.OK.value(), message, null);
    }

    public static CommonApiResponse<?> created(String message) {
        return new CommonApiResponse<>(HttpStatus.CREATED, HttpStatus.CREATED.value(), message, null);
    }

    public static CommonApiResponse<?> error(String message, HttpStatus status) {
        return new CommonApiResponse<>(status, status.value(), message, null);
    }

    public static <T> CommonApiResponse<T> successWithData(String message, T data) {
        return new CommonApiResponse<>(HttpStatus.OK, HttpStatus.OK.value(), message, data);
    }


}
