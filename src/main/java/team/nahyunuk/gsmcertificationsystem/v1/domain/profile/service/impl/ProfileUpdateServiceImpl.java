package team.nahyunuk.gsmcertificationsystem.v1.domain.profile.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.nahyunuk.gsmcertificationsystem.v1.domain.foreign.entity.Foreign;
import team.nahyunuk.gsmcertificationsystem.v1.domain.foreign.repository.ForeignRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.major.entity.Major;
import team.nahyunuk.gsmcertificationsystem.v1.domain.major.repository.MajorRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.profile.dto.request.ProfileUpdateRequest;
import team.nahyunuk.gsmcertificationsystem.v1.domain.profile.entity.Profile;
import team.nahyunuk.gsmcertificationsystem.v1.domain.profile.repository.ProfileRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.profile.service.ProfileUpdateService;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.entity.Student;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.repository.StudentRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.user.entity.User;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.CustomException;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.error.ErrorCode;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;
import team.nahyunuk.gsmcertificationsystem.v1.global.util.UserUtil;

@Service
@RequiredArgsConstructor
public class ProfileUpdateServiceImpl implements ProfileUpdateService {

    private final ProfileRepository profileRepository;
    private final StudentRepository studentRepository;
    private final UserUtil userUtil;
    private final MajorRepository majorRepository;
    private final ForeignRepository foreignRepository;

    @Override
    @Transactional
    public CommonApiResponse execute(ProfileUpdateRequest request) {
        Student student = getStudentByToken();
        Profile profile = profileRepository.findByStudent(student);
        int topcit = findTopcitByMajor(student);
        int toeic = findToeicByForeign(student);

        validateStudentAccess(profile, student);

        profile.update(student.getTotalScore(), request, toeic, topcit);
        return CommonApiResponse.success("프로필이 저장되었습니다.");
    }

    private Student getStudentByToken() {
        User user = userUtil.getCurrentUser();
        return studentRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.STUDENT_NOT_FOUND));
    }

    private void validateStudentAccess(Profile profile, Student student) {
        if (!profile.getStudent().equals(student)) {
            throw new CustomException(ErrorCode.FORBIDDEN_ACCESS);
        }
    }

    private int findTopcitByMajor(Student student) {
        Major major = majorRepository.findByStudent(student);
        return major.getTopcitScore();
    }

    private int findToeicByForeign(Student student) {
        Foreign foreign = foreignRepository.findByStudent(student);
        return foreign.getToeicScore();
    }

}
