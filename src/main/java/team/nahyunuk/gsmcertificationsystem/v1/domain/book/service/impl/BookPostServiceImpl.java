package team.nahyunuk.gsmcertificationsystem.v1.domain.book.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.nahyunuk.gsmcertificationsystem.v1.domain.book.dto.request.BookPostRequest;
import team.nahyunuk.gsmcertificationsystem.v1.domain.book.entity.Book;
import team.nahyunuk.gsmcertificationsystem.v1.domain.book.repository.BookRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.book.service.BookPostService;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.entity.Student;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.repository.StudentRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.user.entity.User;
import team.nahyunuk.gsmcertificationsystem.v1.domain.user.repository.UserRepository;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.CustomException;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.error.ErrorCode;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;
import team.nahyunuk.gsmcertificationsystem.v1.global.security.jwt.TokenProvider;

@Service
@RequiredArgsConstructor
public class BookPostServiceImpl implements BookPostService {

    private final BookRepository bookRepository;
    private final TokenProvider tokenProvider;
    private final StudentRepository studentRepository;

    @Override
    @Transactional
    public CommonApiResponse execute(String token, BookPostRequest request) {
        Student student = getStudentByToken(token);
        saveBook(request, student);
        return CommonApiResponse.success("독서 영역이 저장되었습니다.");
    }

    private Student getStudentByToken(String token) {
        User user = tokenProvider.findUserByToken(token);
        return studentRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.STUDENT_NOT_FOUND));
    }

    private void saveBook(BookPostRequest request, Student student) {
        bookRepository.save(createBook(request, student));
    }

    private Book createBook(BookPostRequest request, Student student) {
        return Book.builder()
                .title(request.title())
                .author(request.author())
                .page(request.page())
                .body(request.body())
                .semester(request.semester())
                .agreement(false)
                .textLength(request.body().length())
                .postStatus(request.postStatus())
                .student(student)
                .build();
    }
}
