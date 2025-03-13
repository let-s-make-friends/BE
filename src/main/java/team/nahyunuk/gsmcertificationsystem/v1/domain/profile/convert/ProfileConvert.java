package team.nahyunuk.gsmcertificationsystem.v1.domain.profile.convert;

import org.springframework.stereotype.Component;
import team.nahyunuk.gsmcertificationsystem.v1.domain.foreign.entity.Foreign;
import team.nahyunuk.gsmcertificationsystem.v1.domain.major.entity.Major;
import team.nahyunuk.gsmcertificationsystem.v1.domain.profile.dto.response.ProfileGetResponse;
import team.nahyunuk.gsmcertificationsystem.v1.domain.profile.entity.Profile;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.entity.Student;

@Component
public class ProfileConvert {

    public ProfileGetResponse getProfile(Profile profile, Major major, Foreign foreign, Student student) {
        int totalScore = major.getMajorTotal() + foreign.getTotalScore();

        return ProfileGetResponse.builder()
                .totalScore(totalScore)
                .username(student.getStudentName())
                .toeicScore(profile.getToeicScore())
                .topcitScore(profile.getTopcitScore())
                .readingMarathon(profile.getReadingMarathon())
                .pdfUrls(profile.getPdfUrls())
                .build();
    }
}
