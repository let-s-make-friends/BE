package team.nahyunuk.gsmcertificationsystem.v1.domain.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.nahyunuk.gsmcertificationsystem.v1.domain.auth.entity.RefreshToken;
import team.nahyunuk.gsmcertificationsystem.v1.domain.auth.repository.RefreshTokenRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.auth.service.ReissueTokenService;
import team.nahyunuk.gsmcertificationsystem.v1.domain.user.repository.UserRepository;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.CustomException;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.error.ErrorCode;
import team.nahyunuk.gsmcertificationsystem.v1.global.security.jwt.TokenProvider;
import team.nahyunuk.gsmcertificationsystem.v1.global.security.jwt.dto.TokenDto;

import static team.nahyunuk.gsmcertificationsystem.v1.global.security.filter.JwtFilter.BEARER_PREFIX;

@Service
@RequiredArgsConstructor
public class ReissueTokenServiceImpl implements ReissueTokenService {

    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    @Transactional
    public TokenDto execute(String token) {
        isNotNullRefreshToken(token);

        String removePrefixToken = token.replaceFirst(BEARER_PREFIX, "").trim();
        RefreshToken refreshToken = refreshTokenRepository.findByToken(removePrefixToken)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_REFRESH_TOKEN));

        String userId = refreshToken.getId()
    }


}
