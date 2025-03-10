package team.nahyunuk.gsmcertificationsystem.v1.domain.major.convert;

import org.springframework.stereotype.Component;
import team.nahyunuk.gsmcertificationsystem.v1.domain.major.dto.response.MajorGetResponse;
import team.nahyunuk.gsmcertificationsystem.v1.domain.major.entity.Major;

@Component
public class MajorConvert {

    public MajorGetResponse getMajor(Major major) {
        return MajorGetResponse.builder()
                .awardCount(major.getAwardCount())
                .licenseCount(major.getLicenseCount())
                .topcitScore(major.getTopcitScore())
                .clubCount(major.getClubCount())
                .competitionCount(major.getCompetitionCount())
                .schoolCompetitionCount(major.getSchoolCompetitionCount())
                .specialLectureCount(major.getSpecialLectureCount())
                .afterSchoolCount(major.getAfterSchoolCount())
                .build();
    }
}
