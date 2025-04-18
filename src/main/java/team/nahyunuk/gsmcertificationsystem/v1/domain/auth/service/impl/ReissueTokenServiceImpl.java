package team.nahyunuk.gsmcertificationsystem.v1.domain.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.nahyunuk.gsmcertificationsystem.v1.domain.auth.entity.RefreshToken;
import team.nahyunuk.gsmcertificationsystem.v1.domain.auth.repository.RefreshTokenRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.auth.service.ReissueTokenService;
import team.nahyunuk.gsmcertificationsystem.v1.domain.user.repository.UserRepository;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.CustomException;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.error.ErrorCode;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;
import team.nahyunuk.gsmcertificationsystem.v1.global.security.jwt.TokenProvider;
import team.nahyunuk.gsmcertificationsystem.v1.global.security.jwt.dto.TokenDto;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReissueTokenServiceImpl implements ReissueTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;

    @Override
    public CommonApiResponse<TokenDto> execute(String token) {
        isNotNullRefreshToken(token);

        String removePrefixToken = tokenProvider.removePrefix(token);
        RefreshToken refreshToken = findRefreshToken(removePrefixToken);

        Long userId = extractUserId(refreshToken);
        validateUserExists(userId);

        TokenDto tokenDto = tokenProvider.generateToken(userId);
        saveRefreshToken(tokenDto.getRefreshToken(), refreshToken.getUserId(), refreshToken.getExpTime());
        return CommonApiResponse.successWithData("토큰이 재발급되었습니다.", tokenDto);
    }

    private Long extractUserId(RefreshToken refreshToken) {
        String userId = tokenProvider.getUserIdFromRefreshToken(refreshToken.getToken());
        return Long.valueOf(userId);
    }

    private void isNotNullRefreshToken(String token) {
        if (token == null) {
            throw new CustomException(ErrorCode.REFRESH_TOKEN_MISSING);
        }
    }

    private RefreshToken findRefreshToken(String token) {
        return refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_REFRESH_TOKEN));
    }

    private void validateUserExists(Long userId) {
        if (!userRepository.existsByUserId(userId)) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }
    }

    private void saveRefreshToken(String token, Long userId, LocalDateTime expiresAt) {
        RefreshToken refreshToken = RefreshToken.builder()
                .userId(userId)
                .token(token)
                .expTime(expiresAt)
                .build();

        refreshTokenRepository.save(refreshToken);
    }

}
