package team.nahyunuk.gsmcertificationsystem.v1.domain.book.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.nahyunuk.gsmcertificationsystem.v1.domain.book.dto.response.BodyGetResponse;
import team.nahyunuk.gsmcertificationsystem.v1.domain.book.entity.Book;
import team.nahyunuk.gsmcertificationsystem.v1.domain.book.repository.BookRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.book.service.BodyGetService;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.CustomException;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.error.ErrorCode;
import team.nahyunuk.gsmcertificationsystem.v1.global.redis.util.RedisUtil;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;

@Service
@RequiredArgsConstructor
public class BodyGetServiceImpl implements BodyGetService {

    private final BookRepository bookRepository;
    private final RedisUtil redisUtil;

    @Override
    @Transactional(readOnly = true)
    public CommonApiResponse execute(Long bookId) {
        String redisKey = "book:" + bookId;
        String value = redisUtil.get(redisKey);

        if (value != null) {
            return CommonApiResponse.successWithData(null, new BodyGetResponse(value));
        }

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new CustomException(ErrorCode.BOOK_NOT_FOUND));

        value = book.getBody();
        redisUtil.set(redisKey, value, 60 * 60);
        return CommonApiResponse.successWithData(null, new BodyGetResponse(value));
    }
}
