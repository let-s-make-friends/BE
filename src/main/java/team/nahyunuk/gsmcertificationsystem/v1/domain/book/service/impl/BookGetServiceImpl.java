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
import team.nahyunuk.gsmcertificationsystem.v1.domain.user.repository.UserRepository;
import team.nahyunuk.gsmcertificationsystem.v1.global.redis.util.RedisUtil;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;
import team.nahyunuk.gsmcertificationsystem.v1.global.security.jwt.TokenProvider;

import java.util.List;
import java.util.StringTokenizer;

@Service
@RequiredArgsConstructor
public class BookGetServiceImpl implements BookGetService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final StudentRepository studentRepository;
    private final RedisUtil redisUtil;
    private final BookConvert bookConvert;

    @Override
    @Transactional(readOnly = true)
    public CommonApiResponse<List<BookGetResponse>> execute(String token, Long bookId) {
        User user= findUserByToken(token);
        Student student = studentRepository.findByEmail(user.getEmail());
        List<BookGetResponse> books = findAllByStudent(student);
        return CommonApiResponse.successWithData(null, books);
    }

    private User findUserByToken(String token) {
        String removeToken = tokenProvider.removePrefix(token);
        String userId = tokenProvider.getUserIdFromAccessToken(removeToken);
        return userRepository.findByUserId(Long.valueOf(userId));
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
}
