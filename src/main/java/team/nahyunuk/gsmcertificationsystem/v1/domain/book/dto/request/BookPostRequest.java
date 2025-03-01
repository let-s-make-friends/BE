package team.nahyunuk.gsmcertificationsystem.v1.domain.book.dto.request;

import jakarta.validation.constraints.NotNull;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.type.PostStatus;

import java.time.LocalDate;

public record BookPostRequest(
        String title,
        String author,
        int page,
        String body,
        LocalDate bookDate,
        int textLength,
        @NotNull PostStatus postStatus
) {
}
