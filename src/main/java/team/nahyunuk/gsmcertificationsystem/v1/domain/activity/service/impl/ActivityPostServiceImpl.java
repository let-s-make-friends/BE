package team.nahyunuk.gsmcertificationsystem.v1.domain.activity.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.dto.request.ActivityPostRequest;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.entity.Activity;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.repository.ActivityRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.service.ActivityPostService;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.entity.Student;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.repository.StudentRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.user.entity.User;
import team.nahyunuk.gsmcertificationsystem.v1.domain.user.repository.UserRepository;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.CustomException;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.error.ErrorCode;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;
import team.nahyunuk.gsmcertificationsystem.v1.global.security.jwt.TokenProvider;

@Service
@RequiredArgsConstructor
public class ActivityPostServiceImpl implements ActivityPostService {

    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;
    private final StudentRepository studentRepository;

    @Override
    @Transactional
    public CommonApiResponse execute(ActivityPostRequest request, String token) {
        User user = findUserByToken(token);
        Activity activity = createActivity(request, user);
        activityRepository.save(activity);
        return CommonApiResponse.success("활동 영역이 저장되었습니다.");
    }

    private User findUserByToken(String token) {
        String removeToken = tokenProvider.removePrefix(token);
        String userId = tokenProvider.getUserIdFromAccessToken(removeToken);
        return userRepository.findByUserId(Long.valueOf(userId));
    }

    private Activity createActivity(ActivityPostRequest request, User user) {
        Student student = studentRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.STUDENT_NOT_FOUND));

        return Activity.builder()
                .activityCategory(request.activityCategory())
                .subject(request.subject())
                .body(request.body())
                .postStatus(request.postStatus())
                .semester(request.semester())
                .textLength(request.body().length())
                .studyCategory(request.studyCategory())
                .imageUrl(request.imageUrl())
                .student(student)
                .agreement(false)
                .build();
    }
}
