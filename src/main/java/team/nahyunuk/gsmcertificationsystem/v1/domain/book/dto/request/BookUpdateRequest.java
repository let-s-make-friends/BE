package team.nahyunuk.gsmcertificationsystem.v1.domain.book.dto.request;

import jakarta.validation.constraints.NotNull;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.type.PostStatus;

public record BookUpdateRequest(
        @NotNull Long id,
        String title,
        String author,
        int page,
        String body,
        int semester,
        @NotNull PostStatus postStatus
) {
}
