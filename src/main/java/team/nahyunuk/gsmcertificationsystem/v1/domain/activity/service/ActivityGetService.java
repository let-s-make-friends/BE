package team.nahyunuk.gsmcertificationsystem.v1.domain.activity.service;

import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.dto.response.ActivityGetResponse;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;

import java.util.List;

public interface ActivityGetService {
    CommonApiResponse<List<ActivityGetResponse>> execute();
}
