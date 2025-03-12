package team.nahyunuk.gsmcertificationsystem.v1.domain.book.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.nahyunuk.gsmcertificationsystem.v1.domain.book.dto.request.BookUpdateRequest;
import team.nahyunuk.gsmcertificationsystem.v1.domain.book.entity.Book;
import team.nahyunuk.gsmcertificationsystem.v1.domain.book.repository.BookRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.book.service.BookUpdateService;
import team.nahyunuk.gsmcertificationsystem.v1.domain.user.entity.User;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.CustomException;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.error.ErrorCode;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;
import team.nahyunuk.gsmcertificationsystem.v1.global.security.jwt.TokenProvider;
import team.nahyunuk.gsmcertificationsystem.v1.global.util.UserUtil;

@Service
@RequiredArgsConstructor
public class BookUpdateServiceImpl implements BookUpdateService {

    private final BookRepository bookRepository;
    private final TokenProvider tokenProvider;
    private final UserUtil userUtil;

    @Override
    @Transactional
    public CommonApiResponse execute(BookUpdateRequest request) {
        User user = userUtil.getCurrentUser();
        Book book = findBookById(request.id());

        validateUserAccess(book, user);

        book.update(request);
        return CommonApiResponse.success("독서 영역이 저장되었습니다.");
    }

    private Book findBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.BOOK_NOT_FOUND));
    }

    private void validateUserAccess(Book book, User user) {
        if (!book.getStudent().getEmail().equals(user.getEmail())) {
            throw new CustomException(ErrorCode.FORBIDDEN_ACCESS);
        }
    }
}
