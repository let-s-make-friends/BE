package team.nahyunuk.gsmcertificationsystem.v1.domain.book.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.nahyunuk.gsmcertificationsystem.v1.domain.book.convert.BookConvert;
import team.nahyunuk.gsmcertificationsystem.v1.domain.book.dto.response.BookGetResponse;
import team.nahyunuk.gsmcertificationsystem.v1.domain.book.repository.BookRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.book.service.BookGetService;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.entity.Student;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.repository.StudentRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.user.entity.User;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.CustomException;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.error.ErrorCode;
import team.nahyunuk.gsmcertificationsystem.v1.global.redis.util.RedisUtil;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;
import team.nahyunuk.gsmcertificationsystem.v1.global.util.UserUtil;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookGetServiceImpl implements BookGetService {

    private final BookRepository bookRepository;
    private final UserUtil userUtil;
    private final StudentRepository studentRepository;
    private final RedisUtil redisUtil;
    private final BookConvert bookConvert;

    @Override
    @Transactional(readOnly = true)
    public CommonApiResponse<List<BookGetResponse>> execute() {
        Student student = getStudentByToken();
        List<BookGetResponse> books = findAllByStudent(student);
        return CommonApiResponse.successWithData(null, books);
    }

    private List<BookGetResponse> findAllByStudent(Student student) {
        return bookRepository.findAllByStudent(student)
                .stream()
                .map(book -> {
                    String redisKey = "book:" + book.getId();
                    redisUtil.set(redisKey, book.getBody(), 60 * 60);

                    return bookConvert.getBook(book);
                })
                .toList();
    }

    private Student getStudentByToken() {
        User user = userUtil.getCurrentUser();
        return studentRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.STUDENT_NOT_FOUND));
    }
}
