package team.nahyunuk.gsmcertificationsystem.v1.domain.book.repository;

import org.springframework.data.repository.CrudRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.book.entity.Book;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.entity.Student;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findAllByStudent(Student student);
}
