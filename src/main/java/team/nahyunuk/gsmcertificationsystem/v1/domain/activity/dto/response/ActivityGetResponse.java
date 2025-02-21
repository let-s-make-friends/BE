package team.nahyunuk.gsmcertificationsystem.v1.domain.activity.dto.response;

import jakarta.validation.constraints.NotNull;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.type.ActivityCategory;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.type.StudyCategory;

import java.time.LocalDate;

public record ActivityGetResponse(
        Long id,
        @NotNull StudyCategory studyCategory,
        @NotNull ActivityCategory activityCategory,
        String subject,
        String body,
        LocalDate activityDate,
        int textLength,
        String imageUrl
) {
}
