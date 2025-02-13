package team.nahyunuk.gsmcertificationsystem.v1.domain.auth.service;

import team.nahyunuk.gsmcertificationsystem.v1.domain.auth.dto.request.SignUpRequest;
import team.nahyunuk.gsmcertificationsystem.v1.global.common.response.CommonApiResponse;

public interface SignUpService {
    CommonApiResponse execute(SignUpRequest request);
}
