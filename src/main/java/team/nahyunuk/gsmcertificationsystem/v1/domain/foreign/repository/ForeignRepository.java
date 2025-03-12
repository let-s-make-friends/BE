package team.nahyunuk.gsmcertificationsystem.v1.domain.foreign.repository;

import org.springframework.data.repository.CrudRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.foreign.entity.Foreign;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.entity.Student;

public interface ForeignRepository extends CrudRepository<Foreign, Long> {
    Foreign findByStudent(Student student);
}
