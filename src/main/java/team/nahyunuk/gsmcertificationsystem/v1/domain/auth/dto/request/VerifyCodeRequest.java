package team.nahyunuk.gsmcertificationsystem.v1.domain.auth.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VerifyCodeRequest {

    @NotNull(message = "이메일은 필수 입력값입니다.")
    private String email;

    @NotNull(message = "인증 번호는 필수 입력값입니다.")
    private String code;
}
