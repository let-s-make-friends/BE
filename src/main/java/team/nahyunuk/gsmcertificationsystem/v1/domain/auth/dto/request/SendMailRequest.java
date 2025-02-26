package team.nahyunuk.gsmcertificationsystem.v1.domain.auth.dto.request;

import jakarta.validation.constraints.NotNull;

public record SendMailRequest (
    @NotNull String email
) {
}
