package team.nahyunuk.gsmcertificationsystem.v1.domain.activity.dto.request;

import jakarta.validation.constraints.NotNull;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.type.ActivityCategory;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.type.PostStatus;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.type.StudyCategory;

public record ActivityPostRequest(

        @NotNull StudyCategory studyCategory,
        @NotNull ActivityCategory activityCategory,
        String subject,
        String body,
        int semester,
        String imageUrl,
        @NotNull PostStatus postStatus
        ) {
}
