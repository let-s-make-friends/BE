package team.nahyunuk.gsmcertificationsystem.v1.domain.activity.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.dto.request.ActivityUpdateRequest;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.entity.Activity;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.repository.ActivityRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.service.ActivityUpdateService;
import team.nahyunuk.gsmcertificationsystem.v1.domain.user.entity.User;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.CustomException;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.error.ErrorCode;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;
import team.nahyunuk.gsmcertificationsystem.v1.global.util.UserUtil;

@Service
@RequiredArgsConstructor
public class AcvitityUpdateServiceImpl implements ActivityUpdateService {

    private final ActivityRepository activityRepository;
    private final UserUtil userUtil;

    @Override
    @Transactional
    public CommonApiResponse execute(ActivityUpdateRequest request) {
        User user = userUtil.getCurrentUser();
        Activity activity = findActivityById(request.id());

        validateUserAccess(activity, user);

        activity.update(request);
        return CommonApiResponse.success("활동 영역이 저장되었습니다");
    }

    private Activity findActivityById(Long id) {
        return activityRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.ACTIVITY_NOT_FOUND));
    }

    private void validateUserAccess(Activity activity, User user) {
        if (!activity.getStudent().getEmail().equals(user.getEmail())) {
            throw new CustomException(ErrorCode.FORBIDDEN_ACCESS);
        }
    }
}
