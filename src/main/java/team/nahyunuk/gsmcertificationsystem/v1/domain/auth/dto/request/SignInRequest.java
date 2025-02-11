package team.nahyunuk.gsmcertificationsystem.v1.domain.auth.dto.request;

import lombok.Getter;

@Getter
public class SignInRequest {
    private String email;
    private String password;
}
