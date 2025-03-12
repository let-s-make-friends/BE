package team.nahyunuk.gsmcertificationsystem.v1.domain.auth.service.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import team.nahyunuk.gsmcertificationsystem.v1.domain.auth.dto.request.SignUpRequest;
import team.nahyunuk.gsmcertificationsystem.v1.domain.auth.service.SignUpService;
import team.nahyunuk.gsmcertificationsystem.v1.domain.profile.entity.Profile;
import team.nahyunuk.gsmcertificationsystem.v1.domain.profile.repository.ProfileRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.profile.type.ReadingMarathon;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.entity.Student;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.repository.StudentRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.user.entity.User;
import team.nahyunuk.gsmcertificationsystem.v1.domain.user.repository.UserRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.user.type.Authority;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.CustomException;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.error.ErrorCode;
import team.nahyunuk.gsmcertificationsystem.v1.global.redis.util.RedisUtil;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {

    private final RedisUtil redisUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final StudentRepository studentRepository;
    private final ProfileRepository profileRepository;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public CommonApiResponse execute(@Valid SignUpRequest request) {
        validateSignUpRequest(request);

        User user = createUser(request);
        userRepository.save(user);

        saveProfileIfStudent(user);

        return CommonApiResponse.success("회원가입이 완료되었습니다.");
    }

    private void validateSignUpRequest(SignUpRequest request) {
        checkEmailSendCode(request.email());
        checkVerifyCode(redisUtil.get(request.email()), request.code());
        existsByEmail(request.email());
        validatePassword(request.password());
    }

    private void checkEmailSendCode(String email) {
        if (!redisUtil.hasKey(email)) {
            throw new CustomException(ErrorCode.EMAIL_NOT_PENDING);
        }
    }

    private void checkVerifyCode(String storedCode, String code) {
        if (!storedCode.equals(code)) {
            throw new CustomException(ErrorCode.INVALID_CODE);
        }
    }

    private void existsByEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new CustomException(ErrorCode.USER_ALREADY_EXISTS);
        }
    }

    private void validatePassword(String password) {
        String passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@$!%*?&]{8,}$";
        if (!password.matches(passwordPattern)) {
            throw new CustomException(ErrorCode.INVALID_PASSWORD_FORMAT);
        }
    }

    private User createUser(SignUpRequest request) {
        return User.builder()
                .authority(getUserType(request.email()))
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .build();
    }

    private Authority getUserType(String email) {
        if (studentRepository.existsByEmail(email)) {
            return Authority.STUDENT;
        } else {
            return Authority.TEACHER;
        }
    }

    private void saveProfileIfStudent(User user) {
        if (user.getAuthority() == Authority.STUDENT) {
            Student student = studentRepository.findByEmail(user.getEmail())
                    .orElseThrow(() -> new CustomException(ErrorCode.STUDENT_NOT_FOUND));

            Profile profile = Profile.builder()
                    .toeicScore(0)
                    .topcitScore(0)
                    .readingMarathon(ReadingMarathon.NONE)
                    .pdfUrls(new ArrayList<>())
                    .student(student)
                    .build();

            profileRepository.save(profile);
        }
    }
}
