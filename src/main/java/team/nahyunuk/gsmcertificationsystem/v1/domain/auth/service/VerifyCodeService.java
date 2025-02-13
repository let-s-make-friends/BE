package team.nahyunuk.gsmcertificationsystem.v1.domain.auth.service;

import team.nahyunuk.gsmcertificationsystem.v1.domain.auth.dto.request.VerifyCodeRequest;
import team.nahyunuk.gsmcertificationsystem.v1.global.common.response.CommonApiResponse;

public interface VerifyCodeService {
    CommonApiResponse execute(VerifyCodeRequest request);
}
