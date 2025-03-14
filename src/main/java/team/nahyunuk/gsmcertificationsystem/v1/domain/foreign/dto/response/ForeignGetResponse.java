package team.nahyunuk.gsmcertificationsystem.v1.domain.foreign.dto.response;

import lombok.Builder;

@Builder
public record ForeignGetResponse(
        int toeicScore,
        int toeflScore,
        int tepsScore,
        int toeicSpeakingLevel,
        String opicScore,
        int jptScore,
        int cptScore,
        int hskScore,
        int totalScore
) {
}
