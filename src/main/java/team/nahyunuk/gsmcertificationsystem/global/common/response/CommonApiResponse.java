package team.nahyunuk.gsmcertificationsystemv1.global.common.response;

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

}
