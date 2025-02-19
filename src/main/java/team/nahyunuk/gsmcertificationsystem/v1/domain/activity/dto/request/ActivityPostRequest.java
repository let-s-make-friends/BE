package team.nahyunuk.gsmcertificationsystem.v1.domain.activity.dto.request;

import jakarta.validation.constraints.NotNull;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.type.ActivityCategory;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.type.PostStatus;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.type.StudyCategory;

import java.time.LocalDate;

public record ActivityPostRequest(

        @NotNull StudyCategory studyCategory,
        @NotNull ActivityCategory activityCategory,
        String body,
        LocalDate activityDate,
        int textLength,
        String imageUrl,
        @NotNull PostStatus postStatus
        ) {
}
