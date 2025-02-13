package team.nahyunuk.gsmcertificationsystem.v1.domain.auth.service;

import jakarta.validation.Valid;
import team.nahyunuk.gsmcertificationsystem.v1.domain.auth.dto.request.SignInRequest;
import team.nahyunuk.gsmcertificationsystem.v1.global.common.response.CommonApiResponse;
import team.nahyunuk.gsmcertificationsystem.v1.global.security.jwt.dto.TokenDto;

public interface SignInService {
    CommonApiResponse<TokenDto> execute(@Valid SignInRequest request);
}
