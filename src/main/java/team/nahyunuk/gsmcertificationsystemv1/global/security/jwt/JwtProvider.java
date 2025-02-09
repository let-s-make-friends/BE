package team.nahyunuk.gsmcertificationsystemv1.global.security.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import team.nahyunuk.gsmcertificationsystemv1.domain.user.type.Authority;
import team.nahyunuk.gsmcertificationsystemv1.global.exception.CustomException;
import team.nahyunuk.gsmcertificationsystemv1.global.exception.error.ErrorCode;
import team.nahyunuk.gsmcertificationsystemv1.global.redis.util.RedisUtil;
import team.nahyunuk.gsmcertificationsystemv1.global.security.jwt.dto.TokenDto;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JwtProvider {
    private static final String AUTHORITIES_KEY = "auth";
    private static final String BEARER_TYPE = "Bearer ";
    private static final long ACCESS_TOKEN_TIME = 60L * 30 * 4;
    private static final long REFRESH_TOKEN_TIME = 60L * 60 * 24 * 7;
    private final RedisUtil redisUtil;

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
                .refreshToken(generateRefreshToken(userId))
                .accessTokenExp(LocalDateTime.now().plusSeconds(ACCESS_TOKEN_TIME))
                .refreshTokenExp(LocalDateTime.now().plusSeconds(REFRESH_TOKEN_TIME))
                .build();
    }

    private String generateAccessToken(UUID userId, Authority authority) {
        Date accessTokenExp = new Date(System.currentTimeMillis() + ACCESS_TOKEN_TIME * 1000);

        return Jwts.builder()
                .setSubject(userId.toString())
                .claim(AUTHORITIES_KEY, authority)
                .setIssuedAt(new Date())
                .setExpiration(accessTokenExp)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    private String generateRefreshToken(UUID userId) {
        Date refreshTokenExp = new Date(System.currentTimeMillis() + REFRESH_TOKEN_TIME * 1000);

        return Jwts.builder()
                .setSubject(userId.toString())
                .setExpiration(refreshTokenExp)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return !redisUtil.hasKayBlackList(token);
        } catch (ExpiredJwtException e) {
            throw new CustomException(ErrorCode.EXPIRED_TOKEN);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }
    }
}
