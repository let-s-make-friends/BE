package team.nahyunuk.gsmcertificationsystem.v1.domain.profile.service;

import team.nahyunuk.gsmcertificationsystem.v1.domain.profile.dto.request.ProfileUpdateRequest;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;

public interface ProfileUpdateService {
    CommonApiResponse execute(ProfileUpdateRequest request);
}
