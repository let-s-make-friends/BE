package team.nahyunuk.gsmcertificationsystem.v1.domain.auth.service.impl;

import io.jsonwebtoken.JwtParser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import team.nahyunuk.gsmcertificationsystem.v1.domain.auth.entity.RefreshToken;
import team.nahyunuk.gsmcertificationsystem.v1.domain.auth.repository.RefreshTokenRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.auth.service.LogOutService;
import team.nahyunuk.gsmcertificationsystem.v1.domain.user.entity.User;
import team.nahyunuk.gsmcertificationsystem.v1.domain.user.repository.UserRepository;
import team.nahyunuk.gsmcertificationsystem.v1.global.common.response.CommonApiResponse;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.CustomException;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.error.ErrorCode;
import team.nahyunuk.gsmcertificationsystem.v1.global.redis.util.RedisUtil;
import team.nahyunuk.gsmcertificationsystem.v1.global.security.jwt.TokenProvider;

@Service
@RequiredArgsConstructor
public class LogOutServiceImpl implements LogOutService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final RedisUtil redisUtil;
    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;

    @Override
    public CommonApiResponse execute(String token) {
        String userId = tokenProvider.getUserIdFromAccessToken(token);
        RefreshToken refreshToken = refreshTokenRepository.findById(Long.valueOf(userId))
                .orElseThrow(() -> new CustomException(ErrorCode.EXPIRED_TOKEN));

        refreshTokenRepository.delete(refreshToken);
        redisUtil.setBlackList(token, "access_token", tokenProvider.getExpiration(token));

        return CommonApiResponse.success("로그아웃 되었습니다");
    }

}
