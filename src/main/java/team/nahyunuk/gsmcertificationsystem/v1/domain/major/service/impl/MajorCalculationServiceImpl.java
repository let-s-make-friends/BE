package team.nahyunuk.gsmcertificationsystem.v1.domain.major.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.nahyunuk.gsmcertificationsystem.v1.domain.major.dto.request.MajorCalculationRequest;
import team.nahyunuk.gsmcertificationsystem.v1.domain.major.entity.Major;
import team.nahyunuk.gsmcertificationsystem.v1.domain.major.repository.MajorRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.major.service.MajorCalculationService;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.entity.Student;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.repository.StudentRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.user.entity.User;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.CustomException;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.error.ErrorCode;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;
import team.nahyunuk.gsmcertificationsystem.v1.global.security.jwt.TokenProvider;

@Service
@RequiredArgsConstructor
public class MajorCalculationServiceImpl implements MajorCalculationService {

    private final MajorRepository majorRepository;
    private final TokenProvider tokenProvider;
    private final StudentRepository studentRepository;

    @Override
    @Transactional
    public CommonApiResponse execute(String token, MajorCalculationRequest request) {
        Student student = getStudentByToken(token);
        Major major = createMajor(request, student);
        majorRepository.save(major);
        return CommonApiResponse.success("전공 영역이 저장되었습니다");
    }

    private Student getStudentByToken(String token) {
        User user = tokenProvider.findUserByToken(token);
        return studentRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.STUDENT_NOT_FOUND));
    }

    private Major createMajor(MajorCalculationRequest request, Student student) {
        int topcitScore = calculateTopcitScore(request.topcitScore());
        int totalScore = calculateTotalScore(request, topcitScore);

        return Major.builder()
                .awardCount(request.awardCount())
                .licenseCount(request.licenseCount())
                .topcitScore(topcitScore)
                .clubCount(request.clubCount())
                .competitionCount(request.competitionCount())
                .specialLectureCount(request.specialLectureCount())
                .afterSchoolCount(request.afterSchoolCount())
                .schoolCompetitionCount(request.schoolCompetitionCount())
                .majorTotal(totalScore)
                .student(student)
                .build();
    }

    private int calculateTopcitScore(int rawScore) {
        return Math.min((int) (rawScore * 3.3), 1000);
    }

    private int calculateTotalScore(MajorCalculationRequest request, int topcitScore) {
        return (request.awardCount() * 50)
                + (request.licenseCount() * 50)
                + topcitScore
                + (request.clubCount() * 50)
                + (request.competitionCount() * 25)
                + (request.schoolCompetitionCount() * 50)
                + (request.specialLectureCount() * 5)
                + (request.afterSchoolCount() * 15);
    }
}
