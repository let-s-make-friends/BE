package team.nahyunuk.gsmcertificationsystem.v1.domain.auth.dto.request;

import jakarta.validation.constraints.NotNull;

public record SignUpRequest (
    @NotNull String email,
    @NotNull String password,
    @NotNull String code
){
}
