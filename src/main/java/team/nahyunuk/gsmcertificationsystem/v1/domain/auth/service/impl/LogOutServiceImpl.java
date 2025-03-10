package team.nahyunuk.gsmcertificationsystem.v1.domain.auth.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import team.nahyunuk.gsmcertificationsystem.v1.domain.auth.entity.RefreshToken;
import team.nahyunuk.gsmcertificationsystem.v1.domain.auth.repository.RefreshTokenRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.auth.service.LogOutService;
import team.nahyunuk.gsmcertificationsystem.v1.domain.user.repository.UserRepository;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.CustomException;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.error.ErrorCode;
import team.nahyunuk.gsmcertificationsystem.v1.global.redis.util.RedisUtil;
import team.nahyunuk.gsmcertificationsystem.v1.global.security.jwt.TokenProvider;

@Slf4j
@Service
@RequiredArgsConstructor
public class LogOutServiceImpl implements LogOutService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final RedisUtil redisUtil;
    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;

    @Override
    public CommonApiResponse execute(String token) {
        log.info("Received JWT Token: '{}'", token);
        validateToken(token);

        String accessToken = tokenProvider.removePrefix(token);
        Long userId = extractUserId(accessToken);
        validateUserExists(userId);

        RefreshToken refreshToken = findRefreshTokenByUserId(userId);
        deleteRefreshToken(refreshToken);
        addTokenToBlackList(accessToken);
        return CommonApiResponse.success("로그아웃 되었습니다.");
    }

    private void validateToken(String token) {
        if (token == null || token.isEmpty()) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }
    }

    private Long extractUserId(String accessToken) {
        String userId = tokenProvider.getUserIdFromAccessToken(accessToken);
        return Long.valueOf(userId);
    }

    private void validateUserExists(Long userId) {
        if (!userRepository.existsByUserId(userId)) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }
    }

    private RefreshToken findRefreshTokenByUserId(Long userId) {
        return refreshTokenRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.EXPIRED_TOKEN));
    }

    private void deleteRefreshToken(RefreshToken refreshToken) {
        refreshTokenRepository.delete(refreshToken);
    }

    private void addTokenToBlackList(String token) {
        long exp = tokenProvider.getExpiration(token);
        redisUtil.setBlackList(token, "access_token", exp);
        log.info("Add token to black list: '{}'", token);
    }

}
