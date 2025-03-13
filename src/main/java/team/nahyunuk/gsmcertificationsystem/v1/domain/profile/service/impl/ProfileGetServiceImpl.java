package team.nahyunuk.gsmcertificationsystem.v1.domain.profile.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.nahyunuk.gsmcertificationsystem.v1.domain.foreign.entity.Foreign;
import team.nahyunuk.gsmcertificationsystem.v1.domain.foreign.repository.ForeignRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.major.entity.Major;
import team.nahyunuk.gsmcertificationsystem.v1.domain.major.repository.MajorRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.profile.convert.ProfileConvert;
import team.nahyunuk.gsmcertificationsystem.v1.domain.profile.entity.Profile;
import team.nahyunuk.gsmcertificationsystem.v1.domain.profile.repository.ProfileRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.profile.service.ProfileGetService;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.entity.Student;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.repository.StudentRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.user.entity.User;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.CustomException;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.error.ErrorCode;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;
import team.nahyunuk.gsmcertificationsystem.v1.global.util.UserUtil;

@Service
@RequiredArgsConstructor
public class ProfileGetServiceImpl implements ProfileGetService {

    private final ProfileRepository profileRepository;
    private final UserUtil userUtil;
    private final StudentRepository studentRepository;
    private final ProfileConvert profileConvert;
    private final MajorRepository majorRepository;
    private final ForeignRepository foreignRepository;

    @Override
    @Transactional(readOnly = true)
    public CommonApiResponse execute() {
        Student student = getStudentByToken();
        Major major = getMajorByStudent(student);
        Foreign foreign = getForeignByStudent(student);
        Profile profile = getProfileByStudent(student);
        return CommonApiResponse.successWithData(null, profileConvert.getProfile(profile, major, foreign, student));
    }

    private Student getStudentByToken() {
        User user = userUtil.getCurrentUser();
        return studentRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.STUDENT_NOT_FOUND));
    }

    private Major getMajorByStudent(Student student) {
        return majorRepository.findByStudent(student);
    }

    private Foreign getForeignByStudent(Student student) {
        return foreignRepository.findByStudent(student);
    }

    private Profile getProfileByStudent(Student student) {
        return profileRepository.findByStudent(student);
    }
}
