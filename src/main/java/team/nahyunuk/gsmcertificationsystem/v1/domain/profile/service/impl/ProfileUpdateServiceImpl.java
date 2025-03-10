package team.nahyunuk.gsmcertificationsystem.v1.domain.profile.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
import team.nahyunuk.gsmcertificationsystem.v1.global.security.jwt.TokenProvider;

@Service
@RequiredArgsConstructor
public class ProfileUpdateServiceImpl implements ProfileUpdateService {

    private final ProfileRepository profileRepository;
    private final StudentRepository studentRepository;
    private final TokenProvider tokenProvider;

    @Override
    @Transactional
    public CommonApiResponse execute(ProfileUpdateRequest request, String token) {
        Student student = getStudentByToken(token);
        Profile profile = profileRepository.findByStudent(student);

        if (!profile.getStudent().equals(student)) {
            throw new CustomException(ErrorCode.FORBIDDEN_ACCESS);
        }

        profile.update(request);
        return CommonApiResponse.success("프로필이 저장되었습니다.");
    }

    private Student getStudentByToken(String token) {
        User user = tokenProvider.findUserByToken(token);
        return studentRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.STUDENT_NOT_FOUND));
    }
}
