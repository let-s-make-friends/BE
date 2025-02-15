package team.nahyunuk.gsmcertificationsystem.v1.domain.auth.service.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import team.nahyunuk.gsmcertificationsystem.v1.domain.auth.dto.request.SignUpRequest;
import team.nahyunuk.gsmcertificationsystem.v1.domain.auth.service.SignUpService;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.entity.Student;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.repository.StudentRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.user.entity.User;
import team.nahyunuk.gsmcertificationsystem.v1.domain.user.repository.UserRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.user.type.Authority;
import team.nahyunuk.gsmcertificationsystem.v1.global.common.response.CommonApiResponse;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.CustomException;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.error.ErrorCode;
import team.nahyunuk.gsmcertificationsystem.v1.global.redis.util.RedisUtil;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {

    private final RedisUtil redisUtil;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public CommonApiResponse execute(@Valid SignUpRequest request) {
        existsByEmail(request.getEmail());
        checkEmailVerified(request);
        User newUser = createUser(request);
        userRepository.save(newUser);
        return CommonApiResponse.success("회원가입이 완료되었습니다");
    }

    private void existsByEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new CustomException(ErrorCode.USER_ALREADY_EXISTS);
        }
    }

    private void checkEmailVerified(SignUpRequest request) {
        String emailVerified = redisUtil.get("verified:" + request.getEmail());
        if (!"true".equals(emailVerified)) {
            throw new CustomException(ErrorCode.EMAIL_NOT_VERIFIED);
        }
        redisUtil.delete("verified:" + request.getEmail());
    }

    private User createUser(SignUpRequest request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        Student student = studentRepository.findByEmail(request.getEmail());
        return User.builder()
                .userId(student.getUserId())
                .authority(Authority.STUDENT)
                .email(request.getEmail())
                .password(encodedPassword)
                .student(student)
                .build();
    }
}
