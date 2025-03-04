package team.nahyunuk.gsmcertificationsystem.v1.domain.book.dto.response;

import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.type.PostStatus;

import java.time.LocalDate;

public record BookGetResponse(
        Long id,
        String title,
        String author,
        int page,
        int semester,
        PostStatus postStatus,
        int textLength
) {
}
