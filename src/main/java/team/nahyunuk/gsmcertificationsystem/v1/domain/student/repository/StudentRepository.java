package team.nahyunuk.gsmcertificationsystem.v1.domain.student.repository;

import org.springframework.data.repository.CrudRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.entity.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {
    Student findByEmail(String email);
    String findStudentNameByEmail(String email);
}
