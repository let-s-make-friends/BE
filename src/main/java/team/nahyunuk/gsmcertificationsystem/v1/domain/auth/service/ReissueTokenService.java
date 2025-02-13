package team.nahyunuk.gsmcertificationsystem.v1.domain.auth.service;

import team.nahyunuk.gsmcertificationsystem.v1.global.common.response.CommonApiResponse;
import team.nahyunuk.gsmcertificationsystem.v1.global.security.jwt.dto.TokenDto;

public interface ReissueTokenService {
    CommonApiResponse<TokenDto> execute(String token);
}
