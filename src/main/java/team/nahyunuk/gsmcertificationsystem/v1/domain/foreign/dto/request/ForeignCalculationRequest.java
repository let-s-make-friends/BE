package team.nahyunuk.gsmcertificationsystem.v1.domain.foreign.dto.request;

public record ForeignCalculationRequest(
        int toeicScore,
        int toeflScore,
        int toeicSpeakingScore,
        int opicScore,
        int jptScore,
        int cptScore,
        int hskScore
) {
}
