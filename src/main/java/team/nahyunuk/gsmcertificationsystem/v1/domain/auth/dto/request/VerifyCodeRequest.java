package team.nahyunuk.gsmcertificationsystem.v1.domain.auth.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VerifyCodeRequest {

    @NotNull
    private String email;

    @NotNull
    private String code;
}
