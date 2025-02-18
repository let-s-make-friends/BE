package team.nahyunuk.gsmcertificationsystem.v1.domain.activity.repository;

import org.springframework.data.repository.CrudRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.entity.Activity;

public interface ActivityRepository extends CrudRepository<Activity, Long> {
}
