package team.nahyunuk.gsmcertificationsystem.v1.domain.profile.repository;

import org.springframework.data.repository.CrudRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.profile.entity.Profile;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.entity.Student;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
    Profile findByStudent(Student student);
}
