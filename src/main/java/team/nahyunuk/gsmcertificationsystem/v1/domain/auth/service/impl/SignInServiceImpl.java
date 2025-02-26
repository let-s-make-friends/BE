package team.nahyunuk.gsmcertificationsystem.v1.domain.auth.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import team.nahyunuk.gsmcertificationsystem.v1.domain.auth.dto.request.SignInRequest;
import team.nahyunuk.gsmcertificationsystem.v1.domain.auth.dto.response.SignInResponse;
import team.nahyunuk.gsmcertificationsystem.v1.domain.auth.entity.RefreshToken;
import team.nahyunuk.gsmcertificationsystem.v1.domain.auth.repository.RefreshTokenRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.auth.service.SignInService;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.entity.Student;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.repository.StudentRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.user.entity.User;
import team.nahyunuk.gsmcertificationsystem.v1.domain.user.repository.UserRepository;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.CustomException;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.error.ErrorCode;
import team.nahyunuk.gsmcertificationsystem.v1.global.security.jwt.TokenProvider;
import team.nahyunuk.gsmcertificationsystem.v1.global.security.jwt.dto.TokenDto;

@Slf4j
@Service
@RequiredArgsConstructor
public class SignInServiceImpl implements SignInService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final StudentRepository studentRepository;

    @Override
    public CommonApiResponse<SignInResponse> execute(SignInRequest request) {
        User user = getUserByEmail(request.email());
        checkPassword(request.password(), user);
        TokenDto tokenDto = tokenProvider.generateToken(user.getUserId());
        saveRefreshToken(user.getUserId(), tokenDto);
        Student student = studentRepository.findByEmail(request.email());
        return CommonApiResponse.successWithData("로그인 성공", new SignInResponse(student.getStudentName(), tokenDto));
    }

    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }

    private void checkPassword(String password, User user) {
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new  CustomException(ErrorCode.INVALID_PASSWORD);
        }
    }

    private void saveRefreshToken(Long userId, TokenDto token) {
        if (token.getRefreshToken() == null || token.getRefreshTokenExp() == null) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }
        RefreshToken refreshToken = createRefreshToken(userId, token);
        log.info("리프레시 토큰이 저장되었습니다: {}", refreshToken);
        refreshTokenRepository.save(refreshToken);
    }

    private RefreshToken createRefreshToken(Long userId, TokenDto token) {
        return RefreshToken.builder()
                .userId(userId)
                .token(token.getRefreshToken())
                .expTime(token.getRefreshTokenExp())
                .build();
    }
}
