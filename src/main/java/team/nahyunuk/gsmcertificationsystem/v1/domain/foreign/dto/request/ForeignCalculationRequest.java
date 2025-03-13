package team.nahyunuk.gsmcertificationsystem.v1.domain.foreign.dto.request;

public record ForeignCalculationRequest(
        int toeicScore,
        int toeflScore,
        int tepsScore,
        int toeicSpeakinglevel,
        String opicScore,
        int jptScore,
        int cptScore,
        int hskScore
) {
}
