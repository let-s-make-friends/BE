package team.nahyunuk.gsmcertificationsystem.v1.domain.major.service;

import team.nahyunuk.gsmcertificationsystem.v1.domain.major.dto.request.MajorCalculationRequest;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;

public interface MajorCalculationService {
    CommonApiResponse execute(String token, MajorCalculationRequest request);
}
