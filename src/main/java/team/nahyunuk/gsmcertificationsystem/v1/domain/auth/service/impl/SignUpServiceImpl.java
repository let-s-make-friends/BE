package team.nahyunuk.gsmcertificationsystem.v1.domain.auth.service.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import team.nahyunuk.gsmcertificationsystem.v1.domain.auth.dto.request.SignUpRequest;
import team.nahyunuk.gsmcertificationsystem.v1.domain.auth.service.SignUpService;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.repository.StudentRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.user.entity.User;
import team.nahyunuk.gsmcertificationsystem.v1.domain.user.repository.UserRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.user.type.Authority;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.CustomException;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.error.ErrorCode;
import team.nahyunuk.gsmcertificationsystem.v1.global.redis.util.RedisUtil;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {

    private final RedisUtil redisUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final StudentRepository studentRepository;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public CommonApiResponse execute(@Valid SignUpRequest request) {
        checkEmailSendCode(request.email());
        String storedCode = redisUtil.get(request.email());
        checkVerifyCode(storedCode, request.code());
        existsByEmail(request.email());
        validatePassword(request.password());
        User user = createUser(request);
        userRepository.save(user);
        return CommonApiResponse.success("회원가입이 완료되었습니다.");
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
        String encodedPassword = passwordEncoder.encode(request.password());

        Authority authority = getUserType(request.email());

        return User.builder()
                .authority(authority)
                .email(request.email())
                .password(encodedPassword)
                .build();
    }

    private Authority getUserType(String email) {
        if (!studentRepository.existsByEmail(email)) {
            return Authority.TEACHER;
        } else {
            return Authority.STUDENT;
        }
    }

}
