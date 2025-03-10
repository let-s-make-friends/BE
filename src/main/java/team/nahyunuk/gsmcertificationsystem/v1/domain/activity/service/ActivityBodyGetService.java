package team.nahyunuk.gsmcertificationsystem.v1.domain.activity.service;

import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;

public interface ActivityBodyGetService {
    CommonApiResponse execute(Long activityId, String token);
}
