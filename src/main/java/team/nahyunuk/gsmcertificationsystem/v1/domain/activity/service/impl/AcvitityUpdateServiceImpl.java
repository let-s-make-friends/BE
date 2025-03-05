package team.nahyunuk.gsmcertificationsystem.v1.domain.activity.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.dto.request.ActivityUpdateRequest;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.entity.Activity;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.repository.ActivityRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.service.ActivityUpdateService;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.CustomException;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.error.ErrorCode;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;

@Service
@RequiredArgsConstructor
public class AcvitityUpdateServiceImpl implements ActivityUpdateService {

    private final ActivityRepository activityRepository;

    @Override
    @Transactional
    public CommonApiResponse execute(ActivityUpdateRequest request) {
        Activity activity = activityRepository.findById(request.id())
                .orElseThrow(() -> new CustomException(ErrorCode.ACTIVITY_NOT_FOUND));

        activity.update(request);
        return CommonApiResponse.success("활동 영역이 저장되었습니다");
    }
}
