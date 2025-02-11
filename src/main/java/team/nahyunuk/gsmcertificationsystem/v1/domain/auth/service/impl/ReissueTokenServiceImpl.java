package team.nahyunuk.gsmcertificationsystem.v1.domain.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.nahyunuk.gsmcertificationsystem.v1.domain.auth.entity.RefreshToken;
import team.nahyunuk.gsmcertificationsystem.v1.domain.auth.repository.RefreshTokenRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.auth.service.ReissueTokenService;
import team.nahyunuk.gsmcertificationsystem.v1.domain.user.entity.User;
import team.nahyunuk.gsmcertificationsystem.v1.domain.user.repository.UserRepository;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.CustomException;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.error.ErrorCode;
import team.nahyunuk.gsmcertificationsystem.v1.global.security.jwt.TokenProvider;
import team.nahyunuk.gsmcertificationsystem.v1.global.security.jwt.dto.TokenDto;

@Service
@RequiredArgsConstructor
public class ReissueTokenServiceImpl implements ReissueTokenService {

    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    @Transactional
    public TokenDto execute(String token) {
        String parseRefreshToken = tokenProvider.parseRefreshToken(token);

        RefreshToken refreshToken = refreshTokenRepository.findByToken(parseRefreshToken)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_REFRESH_TOKEN));

        String userId = tokenProvider.getIdFromRefreshToken(refreshToken.getToken());
        User user = userRepository.findAuthorityByUserId(Long.valueOf(userId));

        TokenDto tokenDto = tokenProvider.generateTokenDto(userId, user.getAuthority());
        saveRefreshToken(tokenDto.getRefreshToken(), refreshToken.getUserId(), refreshToken.getExpTime());

        return tokenDto;
    }

    private void saveRefreshToken(String token, Long userId, int expTime) {
        RefreshToken refreshToken = RefreshToken.builder()
                .userId(userId)
                .token(token)
                .expTime(expTime)
                .build();

        refreshTokenRepository.save(refreshToken);
    }


}
