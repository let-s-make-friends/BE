package team.nahyunuk.gsmcertificationsystem.v1.domain.auth.dto.response;

import team.nahyunuk.gsmcertificationsystem.v1.global.security.jwt.dto.TokenDto;

public record SignInResponse(
        String username,
        TokenDto tokenDto
) {
}
