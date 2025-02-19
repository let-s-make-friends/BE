package team.nahyunuk.gsmcertificationsystem.v1.domain.auth.service;

import jakarta.validation.Valid;
import team.nahyunuk.gsmcertificationsystem.v1.domain.auth.dto.request.SignInRequest;
import team.nahyunuk.gsmcertificationsystem.v1.domain.auth.dto.response.SignInResponse;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;
import team.nahyunuk.gsmcertificationsystem.v1.global.security.jwt.dto.TokenDto;

public interface SignInService {
    CommonApiResponse<SignInResponse> execute(@Valid SignInRequest request);
}
