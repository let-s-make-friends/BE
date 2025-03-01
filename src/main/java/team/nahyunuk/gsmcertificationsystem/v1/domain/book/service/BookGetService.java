package team.nahyunuk.gsmcertificationsystem.v1.domain.book.service;

import team.nahyunuk.gsmcertificationsystem.v1.domain.book.dto.response.BookGetResponse;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;

import java.util.List;

public interface BookGetService {
    CommonApiResponse<List<BookGetResponse>> execute(String token, Long bookId);
}
