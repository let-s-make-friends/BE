package team.nahyunuk.gsmcertificationsystem.v1.domain.profile.convert;

import org.springframework.stereotype.Component;
import team.nahyunuk.gsmcertificationsystem.v1.domain.profile.dto.response.ProfileGetResponse;
import team.nahyunuk.gsmcertificationsystem.v1.domain.profile.entity.Profile;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.entity.Student;

@Component
public class ProfileConvert {

    public ProfileGetResponse getProfile(Profile profile) {
        Student student = profile.getStudent();
        return ProfileGetResponse.builder()
                .totalScore(student.getTotalScore())
                .username(student.getStudentName())
                .toeicScore(profile.getToeicScore())
                .topcitScore(profile.getTopcitScore())
                .readingMarathon(profile.getReadingMarathon())
                .pdfUrls(profile.getPdfUrls())
                .build();
    }
}
