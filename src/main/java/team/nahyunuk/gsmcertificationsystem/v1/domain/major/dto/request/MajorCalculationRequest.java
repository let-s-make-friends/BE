package team.nahyunuk.gsmcertificationsystem.v1.domain.major.dto.request;

import jakarta.validation.constraints.NotNull;

public record MajorCalculationRequest(
        @NotNull int awardCount,
        @NotNull int licenseCount,
        @NotNull int topcitScore,
        @NotNull int clubCount,
        @NotNull int competitionCount,
        @NotNull int schoolCompetitionCount,
        @NotNull int specialLectureCount,
        @NotNull int afterSchoolCount
) {
}
