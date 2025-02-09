package team.nahyunuk.gsmcertificationsystem.v1.global.security.jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class TokenDto {
    private String accessToken;
    private String refreshToken;
    private LocalDateTime accessTokenExp;
    private LocalDateTime refreshTokenExp;
