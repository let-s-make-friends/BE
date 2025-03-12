package team.nahyunuk.gsmcertificationsystem.v1.domain.book.service;

import team.nahyunuk.gsmcertificationsystem.v1.domain.book.dto.request.BookUpdateRequest;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;

public interface BookUpdateService {
    CommonApiResponse execute(BookUpdateRequest request);
}
