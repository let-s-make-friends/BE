package team.nahyunuk.gsmcertificationsystemv1.domain.auth.repository;

import org.springframework.data.repository.CrudRepository;
import team.nahyunuk.gsmcertificationsystemv1.domain.auth.entity.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
}
