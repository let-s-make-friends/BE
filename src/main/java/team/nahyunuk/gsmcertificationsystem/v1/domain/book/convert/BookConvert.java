package team.nahyunuk.gsmcertificationsystem.v1.domain.book.convert;

import org.springframework.stereotype.Component;
import team.nahyunuk.gsmcertificationsystem.v1.domain.book.dto.response.BookGetResponse;
import team.nahyunuk.gsmcertificationsystem.v1.domain.book.entity.Book;

@Component
public class BookConvert {

    public BookGetResponse getBook(Book book) {
        return new BookGetResponse(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPage(),
                book.getBookDate(),
                book.getPostStatus(),
                book.getBody().length()
        );
    }
}
