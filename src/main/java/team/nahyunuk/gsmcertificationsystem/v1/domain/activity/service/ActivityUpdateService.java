package team.nahyunuk.gsmcertificationsystem.v1.domain.activity.service;

import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.dto.request.ActivityUpdateRequest;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;

public interface ActivityUpdateService {
    CommonApiResponse execute(ActivityUpdateRequest request, String token);
}
