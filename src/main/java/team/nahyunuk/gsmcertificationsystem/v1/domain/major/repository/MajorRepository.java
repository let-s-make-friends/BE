package team.nahyunuk.gsmcertificationsystem.v1.domain.major.repository;

import org.springframework.data.repository.CrudRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.major.entity.Major;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.entity.Student;

public interface MajorRepository extends CrudRepository<Major, Long> {
    Major findByStudent(Student student);
}
