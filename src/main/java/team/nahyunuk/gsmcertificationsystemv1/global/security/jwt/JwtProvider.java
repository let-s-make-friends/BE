package team.nahyunuk.gsmcertificationsystemv1.global.security.jwt;

import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import io.jsonwebtoken.io.Decoders;
import team.nahyunuk.gsmcertificationsystemv1.domain.user.type.Authority;
import team.nahyunuk.gsmcertificationsystemv1.global.security.jwt.dto.TokenDto;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JwtProvider {
    private static final String AUTHORITIES_KEY = "auth";
    private static final String BEARER_TYPE = "Bearer ";
    private static final long ACCESS_TOKEN_TIME = 60L * 30 * 4;
    private static final long REFRESH_TOKEN_TIME = 60L * 60 * 24 * 7;

    @Value("${jwt.secret}")
    private String secretKey;

    private static Key key;

    @PostConstruct
    public void init() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        key = Keys.hmacShaKeyFor(keyBytes);
    }

    public TokenDto generateTokenDto(UUID userId, Authority authority) {
        return TokenDto.builder()
                .accessToken(generateAccessToken(userId, authority))
                .refreshToken(generateRefreshToken(userId, authority))
                .accessTokenExp(LocalDateTime.now().plusSeconds(ACCESS_TOKEN_TIME))
                .refreshTokenExp(LocalDateTime.now().plusSeconds(REFRESH_TOKEN_TIME))
                .build();
    }
}
