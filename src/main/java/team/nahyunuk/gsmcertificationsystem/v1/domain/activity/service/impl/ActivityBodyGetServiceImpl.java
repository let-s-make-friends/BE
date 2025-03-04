package team.nahyunuk.gsmcertificationsystem.v1.domain.activity.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.convert.ActivityConvert;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.entity.Activity;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.repository.ActivityRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.service.ActivityBodyGetService;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.CustomException;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.error.ErrorCode;
import team.nahyunuk.gsmcertificationsystem.v1.global.redis.util.RedisUtil;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;

@Service
@RequiredArgsConstructor
public class ActivityBodyGetServiceImpl implements ActivityBodyGetService {

    private final ActivityRepository activityRepository;
    private final RedisUtil redisUtil;
    private final ActivityConvert activityConvert;

    @Override
    @Transactional(readOnly = true)
    public CommonApiResponse execute(Long activityId) {
        String redisKey = "activity:" + activityId;
        String cachedBody = redisUtil.get(redisKey);

        if (cachedBody != null) {
            return CommonApiResponse.successWithData(null, activityConvert.getBody(cachedBody));
        }

        Activity activity = activityRepository.findById(activityId)
                .orElseThrow(() -> new CustomException(ErrorCode.ACTIVITY_NOT_FOUND));

        cachedBody = activity.getBody();
        redisUtil.set(redisKey, cachedBody, 60 * 60);
        return CommonApiResponse.successWithData(null, activityConvert.getBody(cachedBody));
    }


}
