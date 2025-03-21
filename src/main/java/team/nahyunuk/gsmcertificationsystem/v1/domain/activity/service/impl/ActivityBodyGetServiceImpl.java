package team.nahyunuk.gsmcertificationsystem.v1.domain.activity.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.convert.ActivityConvert;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.entity.Activity;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.repository.ActivityRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.service.ActivityBodyGetService;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.entity.Student;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.repository.StudentRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.user.entity.User;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.CustomException;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.error.ErrorCode;
import team.nahyunuk.gsmcertificationsystem.v1.global.redis.util.RedisUtil;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;
import team.nahyunuk.gsmcertificationsystem.v1.global.util.UserUtil;

@Service
@RequiredArgsConstructor
public class ActivityBodyGetServiceImpl implements ActivityBodyGetService {

    private final ActivityRepository activityRepository;
    private final RedisUtil redisUtil;
    private final ActivityConvert activityConvert;
    private final StudentRepository studentRepository;
    private final UserUtil userUtil;

    @Override
    @Transactional(readOnly = true)
    public CommonApiResponse execute(Long activityId) {
        Student student = getStudentByToken();
        String redisKey = "activity:" + activityId;

        String cachedBody = redisUtil.get(redisKey);
        if (cachedBody != null) {
            return CommonApiResponse.successWithData(null, activityConvert.getBody(cachedBody));
        }

        Activity activity = findActivityById(activityId);

        validateActivityAccess(activity, student);

        redisUtil.set(redisKey, activity.getBody(), 60 * 60);
        return CommonApiResponse.successWithData(null, activityConvert.getBody(cachedBody));
    }

    private Student getStudentByToken() {
        User user = userUtil.getCurrentUser();
        return studentRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.STUDENT_NOT_FOUND));
    }

    private void validateActivityAccess(Activity activity, Student student) {
        if (!activity.getStudent().equals(student)) {
            throw new CustomException(ErrorCode.FORBIDDEN_ACCESS);
        }
    }

    private Activity findActivityById(Long activityId) {
        return activityRepository.findById(activityId)
                .orElseThrow(() -> new CustomException(ErrorCode.ACTIVITY_NOT_FOUND));
    }
}
