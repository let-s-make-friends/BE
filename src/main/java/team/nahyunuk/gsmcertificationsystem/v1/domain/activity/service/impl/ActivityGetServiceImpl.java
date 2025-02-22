package team.nahyunuk.gsmcertificationsystem.v1.domain.activity.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.dto.response.ActivityGetResponse;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.service.ActivityGetService;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;

@Service
@RequiredArgsConstructor
public class ActivityGetServiceImpl implements ActivityGetService {

    @Override
    public CommonApiResponse<ActivityGetResponse> execute(String token) {
        return null;
    }
}
