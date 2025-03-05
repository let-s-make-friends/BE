package team.nahyunuk.gsmcertificationsystem.v1.domain.major.service;

import team.nahyunuk.gsmcertificationsystem.v1.domain.major.dto.request.MajorUpdateRequest;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;

public interface MajorUpdateService {
    CommonApiResponse execute(MajorUpdateRequest request, String token);
}
