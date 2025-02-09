package team.nahyunuk.gsmcertificationsystemv1.domain.user.repository;

import org.springframework.data.repository.CrudRepository;
import team.nahyunuk.gsmcertificationsystemv1.domain.user.entity.User;

import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {
}
