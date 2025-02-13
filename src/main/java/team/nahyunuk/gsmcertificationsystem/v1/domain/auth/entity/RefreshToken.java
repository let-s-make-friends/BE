package team.nahyunuk.gsmcertificationsystem.v1.domain.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.time.LocalDateTime;


@Getter
@Builder
@AllArgsConstructor
@RedisHash(value = "refreshToken", timeToLive = 60)
public class RefreshToken {

    @Id
    @Indexed
    private Long userId;

    @Indexed
    private String token;

    private LocalDateTime expTime;

}
