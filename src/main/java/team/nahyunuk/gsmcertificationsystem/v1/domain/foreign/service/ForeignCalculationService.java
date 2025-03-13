package team.nahyunuk.gsmcertificationsystem.v1.domain.foreign.service;

import team.nahyunuk.gsmcertificationsystem.v1.domain.foreign.dto.request.ForeignCalculationRequest;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;

public interface ForeignCalculationService {
    CommonApiResponse execute(ForeignCalculationRequest request);
}
