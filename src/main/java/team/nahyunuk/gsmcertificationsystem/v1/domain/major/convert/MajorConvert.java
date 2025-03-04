package team.nahyunuk.gsmcertificationsystem.v1.domain.major.convert;

import org.springframework.stereotype.Component;
import team.nahyunuk.gsmcertificationsystem.v1.domain.major.dto.response.MajorGetResponse;
import team.nahyunuk.gsmcertificationsystem.v1.domain.major.entity.Major;

@Component
public class MajorConvert {

    public MajorGetResponse getMajor(Major major) {
        return new MajorGetResponse(
                major.getAwardCount(),
                major.getLicenseCount(),
                major.getTopcitScore(),
                major.getClubCount(),
                major.getCompetitionCount(),
                major.getSchoolCompetitionCount(),
                major.getSpecialLectureCount(),
                major.getSchoolCompetitionCount()
                );
    }
}
