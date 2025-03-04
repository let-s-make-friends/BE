package team.nahyunuk.gsmcertificationsystem.v1.domain.major.dto.response;

public record MajorGetResponse(
        int awardCount,
        int licenseCount,
        int topcitScore,
        int clubCount,
        int competitionCount,
        int schoolCompetitionCount,
        int specialLectureCount,
        int afterSchoolCount
) {
}
