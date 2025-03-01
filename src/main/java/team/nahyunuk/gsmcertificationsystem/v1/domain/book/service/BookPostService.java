package team.nahyunuk.gsmcertificationsystem.v1.domain.book.service;

import team.nahyunuk.gsmcertificationsystem.v1.domain.book.dto.request.BookPostRequest;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;

public interface BookPostService {
    CommonApiResponse execute(String token, BookPostRequest request);
}
