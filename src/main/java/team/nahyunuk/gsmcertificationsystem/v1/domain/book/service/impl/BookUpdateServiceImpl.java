package team.nahyunuk.gsmcertificationsystem.v1.domain.book.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.nahyunuk.gsmcertificationsystem.v1.domain.book.dto.request.BookUpdateRequest;
import team.nahyunuk.gsmcertificationsystem.v1.domain.book.entity.Book;
import team.nahyunuk.gsmcertificationsystem.v1.domain.book.repository.BookRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.book.service.BookUpdateService;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.CustomException;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.error.ErrorCode;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;

@Service
@RequiredArgsConstructor
public class BookUpdateServiceImpl implements BookUpdateService {

    private final BookRepository bookRepository;

    @Override
    @Transactional
    public CommonApiResponse execute(BookUpdateRequest request) {
        Book book = bookRepository.findById(request.id())
                .orElseThrow(() -> new CustomException(ErrorCode.BOOK_NOT_FOUND));

        book.update(request);
        bookRepository.save(book);
        return CommonApiResponse.success("독서 영역이 저장되었습니다.");
    }
}
