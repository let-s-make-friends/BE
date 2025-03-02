package team.nahyunuk.gsmcertificationsystem.v1.domain.activity.dto.request;

import jakarta.validation.constraints.NotNull;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.type.ActivityCategory;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.type.PostStatus;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.type.StudyCategory;

import java.time.LocalDate;

public record ActivityUpdateRequest(
        Long id,
        @NotNull StudyCategory studyCategory,
        @NotNull ActivityCategory activityCategory,
        String subject,
        String body,
        LocalDate activityDate,
        String imageUrl,
        @NotNull PostStatus postStatus
        ) {
}
