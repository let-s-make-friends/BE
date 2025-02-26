package team.nahyunuk.gsmcertificationsystem.v1.domain.activity.dto.response;

import jakarta.validation.constraints.NotNull;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.type.ActivityCategory;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.type.PostStatus;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.type.StudyCategory;

import java.time.LocalDate;

public record ActivityGetResponse(
        Long id,
        StudyCategory studyCategory,
        ActivityCategory activityCategory,
        String subject,
        LocalDate activityDate,
        PostStatus postStatus,
        int textLength,
        String imageUrl
) {
}
