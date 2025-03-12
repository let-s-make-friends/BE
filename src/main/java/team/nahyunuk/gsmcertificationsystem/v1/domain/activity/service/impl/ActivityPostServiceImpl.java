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
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.CustomException;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.error.ErrorCode;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;
import team.nahyunuk.gsmcertificationsystem.v1.global.util.UserUtil;

@Service
@RequiredArgsConstructor
public class ActivityPostServiceImpl implements ActivityPostService {

    private final ActivityRepository activityRepository;
    private final StudentRepository studentRepository;
    private final UserUtil userUtil;

    @Override
    @Transactional
    public CommonApiResponse execute(ActivityPostRequest request) {
        Student student = getStudentByToken();
        saveActivity(request, student);
        return CommonApiResponse.success("활동 영역이 저장되었습니다.");
    }

    private void saveActivity(ActivityPostRequest request, Student student) {
        activityRepository.save(createActivity(request, student));
    }

    private Activity createActivity(ActivityPostRequest request, Student student) {
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

    private Student getStudentByToken() {
        User user = userUtil.getCurrentUser();
        return studentRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.STUDENT_NOT_FOUND));
    }
}
