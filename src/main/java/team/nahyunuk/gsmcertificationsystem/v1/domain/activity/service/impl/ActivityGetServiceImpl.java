package team.nahyunuk.gsmcertificationsystem.v1.domain.activity.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.convert.ActivityConvert;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.dto.response.ActivityGetResponse;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.entity.Activity;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.repository.ActivityRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.service.ActivityGetService;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.entity.Student;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.repository.StudentRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.user.entity.User;
import team.nahyunuk.gsmcertificationsystem.v1.domain.user.repository.UserRepository;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.CustomException;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.error.ErrorCode;
import team.nahyunuk.gsmcertificationsystem.v1.global.redis.util.RedisUtil;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;
import team.nahyunuk.gsmcertificationsystem.v1.global.security.jwt.TokenProvider;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityGetServiceImpl implements ActivityGetService {

    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final StudentRepository studentRepository;
    private final ActivityConvert activityConvert;
    private final RedisUtil redisUtil;

    @Override
    @Transactional(readOnly = true)
    public CommonApiResponse<List<ActivityGetResponse>> execute(String token) {
        User user = findUserByToken(token);
        Student student = studentRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.STUDENT_NOT_FOUND));
        List<ActivityGetResponse> activities = findAllByStudent(student);
        return CommonApiResponse.successWithData(null, activities);
    }

    private User findUserByToken(String token) {
        String removeToken = tokenProvider.removePrefix(token);
        String userId = tokenProvider.getUserIdFromAccessToken(removeToken);
        return userRepository.findByUserId(Long.valueOf(userId));
    }

    private List<ActivityGetResponse> findAllByStudent(Student student) {
        return activityRepository.findAllByStudent(student)
                .stream()
                .map(activity -> {
                    String redisKey = "activity:" + activity.getId();
                    redisUtil.set(redisKey, activity.getBody(), 60 * 60);

                    return activityConvert.getActivity(activity);
                })
                .toList();
    }
}
