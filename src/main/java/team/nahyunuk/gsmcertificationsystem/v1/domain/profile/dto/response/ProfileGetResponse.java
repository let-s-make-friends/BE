package team.nahyunuk.gsmcertificationsystem.v1.domain.profile.dto.response;

import lombok.Builder;
import team.nahyunuk.gsmcertificationsystem.v1.domain.profile.type.ReadingMarathon;

import java.util.List;

@Builder
public record ProfileGetResponse(
        int totalScore,
        String username,
        int toeicScore,
        int topcitScore,
        ReadingMarathon readingMarathon,
        List<String> pdfUrls
) {
}
