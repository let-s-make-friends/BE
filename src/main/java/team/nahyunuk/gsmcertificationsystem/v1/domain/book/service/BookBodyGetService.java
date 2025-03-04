package team.nahyunuk.gsmcertificationsystem.v1.domain.book.service;

import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;

public interface BookBodyGetService {
    CommonApiResponse execute(Long bookId);
}
