package team.nahyunuk.gsmcertificationsystem.v1.domain.book.convert;

import org.springframework.stereotype.Component;
import team.nahyunuk.gsmcertificationsystem.v1.domain.book.dto.response.BookGetResponse;
import team.nahyunuk.gsmcertificationsystem.v1.domain.book.entity.Book;

@Component
public class BookConvert {

    public BookGetResponse getBook(Book book) {
        return BookGetResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .page(book.getPage())
                .semester(book.getSemester())
                .postStatus(book.getPostStatus())
                .textLength(book.getBody().length())
                .build();
    }
}
