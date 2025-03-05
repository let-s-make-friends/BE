package team.nahyunuk.gsmcertificationsystem.v1.domain.student.repository;

import org.springframework.data.repository.CrudRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.entity.Student;

import java.util.Optional;

public interface StudentRepository extends CrudRepository<Student, Long> {
    Optional<Student> findByEmail(String email);
    boolean existsByEmail(String email);
}
