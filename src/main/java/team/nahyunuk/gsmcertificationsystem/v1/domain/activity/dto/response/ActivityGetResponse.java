package team.nahyunuk.gsmcertificationsystem.v1.domain.activity.dto.response;

import lombok.Builder;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.type.ActivityCategory;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.type.PostStatus;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.type.StudyCategory;

@Builder
public record ActivityGetResponse(
        Long id,
        StudyCategory studyCategory,
        ActivityCategory activityCategory,
        String subject,
        int semester,
        PostStatus postStatus,
        int textLength,
        String imageUrl
) {
}
