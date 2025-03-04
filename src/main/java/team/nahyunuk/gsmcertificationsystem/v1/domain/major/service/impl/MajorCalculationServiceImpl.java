package team.nahyunuk.gsmcertificationsystem.v1.domain.major.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.nahyunuk.gsmcertificationsystem.v1.domain.major.dto.request.MajorCalculationRequest;
import team.nahyunuk.gsmcertificationsystem.v1.domain.major.entity.Major;
import team.nahyunuk.gsmcertificationsystem.v1.domain.major.repository.MajorRepository;

@Service
@RequiredArgsConstructor
public class MajorCalculationService {

    private final MajorRepository majorRepository;

    public void execute(MajorCalculationRequest request) {
        Major major = createMajor(request);
    }

    private Major createMajor(MajorCalculationRequest request) {
        int topcit = request.topcitScore();
        topcit *= 3.3;

        if (topcit > 1000) {
            topcit = 1000;
        }

        int total = request.awardCount() * 50
                + request.licenseCount() * 50
                + topcit
                + request.clubCount() * 50
                + request.competitionCount() * 25
                + request.schoolCompetitionCount() * 50
                + request.specialLectureCount() * 5
                + request.afterSchoolCount() * 15;

        return Major.builder()
                .awardCount(request.awardCount())
                .licenseCount(request.licenseCount())
                .topcitScore(topcit)
                .clubCount(request.clubCount())
                .competitionCount(request.competitionCount())
                .specialLectureCount(request.specialLectureCount())
                .afterSchoolCount(request.afterSchoolCount())
                .majorTotal(total)
                .build();

    }
}
