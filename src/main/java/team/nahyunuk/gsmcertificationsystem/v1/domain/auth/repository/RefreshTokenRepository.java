package team.nahyunuk.gsmcertificationsystem.v1.domain.auth.repository;

import org.springframework.data.repository.CrudRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.auth.entity.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
    Optional<RefreshToken> findByToken(String token);
}
