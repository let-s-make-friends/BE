package team.nahyunuk.gsmcertificationsystem.v1.domain.profile.repository;

import org.springframework.data.repository.CrudRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.profile.entity.Profile;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
}
