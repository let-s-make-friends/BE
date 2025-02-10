package team.nahyunuk.gsmcertificationsystem.v1.domain.auth.service;

import team.nahyunuk.gsmcertificationsystem.v1.global.security.jwt.dto.TokenDto;

public interface ReissueTokenService {
    TokenDto execute(String token);
}
