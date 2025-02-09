package team.nahyunuk.gsmcertificationsystem.domain.auth.repository;

import org.springframework.data.repository.CrudRepository;
import team.nahyunuk.gsmcertificationsystem.domain.auth.entity.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
}
