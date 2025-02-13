package team.nahyunuk.gsmcertificationsystem.v1.domain.auth.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import team.nahyunuk.gsmcertificationsystem.v1.domain.auth.entity.RefreshToken;
import team.nahyunuk.gsmcertificationsystem.v1.domain.auth.repository.RefreshTokenRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.auth.service.LogOutService;
import team.nahyunuk.gsmcertificationsystem.v1.domain.user.repository.UserRepository;
import team.nahyunuk.gsmcertificationsystem.v1.global.common.response.CommonApiResponse;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.CustomException;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.error.ErrorCode;
import team.nahyunuk.gsmcertificationsystem.v1.global.redis.util.RedisUtil;
import team.nahyunuk.gsmcertificationsystem.v1.global.security.jwt.TokenProvider;

import static team.nahyunuk.gsmcertificationsystem.v1.global.security.filter.JwtFilter.BEARER_PREFIX;

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
        String removeToken = tokenProvider.removePrefix(token);
        String userId = tokenProvider.getUserIdFromAccessToken(removeToken);
        RefreshToken refreshToken = refreshTokenRepository.findById(Long.valueOf(userId))
                .orElseThrow(() -> new CustomException(ErrorCode.EXPIRED_TOKEN));

        refreshTokenRepository.delete(refreshToken);
        redisUtil.setBlackList(token, "access_token", tokenProvider.getExpiration(removeToken));

        return CommonApiResponse.success("로그아웃 되었습니다");
    }

}
