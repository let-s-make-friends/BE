package team.nahyunuk.gsmcertificationsystem.v1.domain.activity.service;

import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;

public interface GetBodyService {
    CommonApiResponse execute(String token, Long activityId);
}
