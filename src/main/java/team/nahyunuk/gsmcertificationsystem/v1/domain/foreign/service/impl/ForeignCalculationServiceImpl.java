package team.nahyunuk.gsmcertificationsystem.v1.domain.foreign.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.nahyunuk.gsmcertificationsystem.v1.domain.foreign.dto.request.ForeignCalculationRequest;
import team.nahyunuk.gsmcertificationsystem.v1.domain.foreign.entity.Foreign;
import team.nahyunuk.gsmcertificationsystem.v1.domain.foreign.repository.ForeignRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.foreign.service.ForeignCalculationService;
import team.nahyunuk.gsmcertificationsystem.v1.domain.profile.entity.Profile;
import team.nahyunuk.gsmcertificationsystem.v1.domain.profile.repository.ProfileRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.entity.Student;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.repository.StudentRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.user.entity.User;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.CustomException;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.error.ErrorCode;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;
import team.nahyunuk.gsmcertificationsystem.v1.global.util.UserUtil;

@Service
@RequiredArgsConstructor
public class ForeignCalculationServiceImpl implements ForeignCalculationService {

    private final ForeignRepository foreignRepository;
    private final StudentRepository studentRepository;
    private final ProfileRepository profileRepository;
    private final UserUtil userUtil;

    @Override
    @Transactional
    public CommonApiResponse execute(ForeignCalculationRequest request) {
        Student student = getStudentByToken();
        Foreign foreign = createForeign(request, student);
        saveForeign(foreign);
        Profile profile = findProfileByStudent(student);
        int toeic = calculateToeicScore(request.toeicScore());
        profile.updateToeicScore(toeic);
        return CommonApiResponse.success("외국어 영역이 저장되었습니다.");
    }

    private Student getStudentByToken() {
        User user = userUtil.getCurrentUser();
        return studentRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.STUDENT_NOT_FOUND));
    }

    private Profile findProfileByStudent(Student student) {
        return profileRepository.findByStudent(student);
    }

    private Foreign createForeign(ForeignCalculationRequest request, Student student) {
        int totalScore = calculateTotalScore(request);

        return Foreign.builder()
                .toeicScore(calculateToeicScore(request.toeicScore()))
                .toeflScore(calculateToeflScore(request.toeflScore()))
                .tepsScore(calculateTepsScore(request.tepsScore()))
                .toeicSpeakingLevel(calculateToeicSpeakingScore(request.toeicSpeakinglevel()))
                .opicScore(calculateOpicScore(request.opicScore()))
                .jptScore(calculateJptScore(request.jptScore()))
                .cptScore(calculateCptScore(request.cptScore()))
                .hskScore(calculateHskScore(request.hskScore()))
                .totalScore(totalScore)
                .student(student)
                .build();
    }

    private int calculateToeicScore(int toeicScore) {
        if (toeicScore >= 700) {
            return 500;
        } else if (toeicScore >= 630) {
            return 450;
        } else if (toeicScore >= 560) {
            return 400;
        } else if (toeicScore >= 490) {
            return 350;
        } else if (toeicScore >= 420) {
            return 300;
        } else if (toeicScore >= 300) {
            return 250;
        } else {
            return 0;
        }
    }

    private int calculateToeflScore(int toeflScore) {
        if (toeflScore >= 80) {
            return 500;
        } else if (toeflScore >= 75) {
            return 450;
        } else if (toeflScore >= 70) {
            return 400;
        } else if (toeflScore >= 65) {
            return 350;
        } else if (toeflScore >= 60) {
            return 300;
        } else if (toeflScore >= 55) {
            return 250;
        } else {
            return 0;
        }
    }

    private int calculateTepsScore(int tepsScore) {
        if (tepsScore >= 555) {
            return 500;
        } else if (tepsScore >= 520) {
            return 450;
        } else if (tepsScore >= 480) {
            return 400;
        } else if (tepsScore >= 440) {
            return 350;
        } else if (tepsScore >= 400) {
            return 300;
        } else if (tepsScore >= 360) {
            return 250;
        } else {
            return 0;
        }
    }

    private int calculateToeicSpeakingScore(int toeicSpeakingLevel) {
        if (toeicSpeakingLevel >= 6) {
            return 500;
        } else if (toeicSpeakingLevel >= 5) {
            return 450;
        } else if (toeicSpeakingLevel >= 4) {
            return 400;
        } else if (toeicSpeakingLevel >= 3) {
            return 350;
        } else {
            return 0;
        }
    }

    private int calculateOpicScore(String opicScore) {
        if (opicScore.equals("Novice High")) {
            return 350;
        } else if (opicScore.equals("Int Low")) {
            return 400;
        } else if (opicScore.equals("Int 1")) {
            return 450;
        } else if (opicScore.equals("Int 2") || opicScore.equals("Int 3")) {
            return 500;
        } else {
            return 0;
        }
    }

    private int calculateJptScore(int jptScore) {
        if (jptScore >= 700) {
            return 500;
        } else if (jptScore >= 630) {
            return 450;
        } else if (jptScore >= 560) {
            return 400;
        } else if (jptScore >= 490) {
            return 350;
        } else if (jptScore >= 420) {
            return 300;
        } else if (jptScore >= 350) {
            return 250;
        } else {
            return 0;
        }
    }

    private int calculateCptScore(int cptScore) {
        if (cptScore >= 651) {
            return 500;
        } else if (cptScore >= 501) {
            return 450;
        } else if (cptScore >= 351) {
            return 400;
        } else if (cptScore >= 251) {
            return 350;
        } else {
            return 0;
        }
    }

    private int calculateHskScore(int hskScore) {
        if (hskScore == 6) {
            return 500;
        } else if (hskScore == 5) {
            return 450;
        } else if (hskScore == 4) {
            return 400;
        } else if (hskScore == 3) {
            return 350;
        } else if (hskScore == 2) {
            return 300;
        } else {
            return 0;
        }
    }

    private void saveForeign(Foreign foreign) {
        foreignRepository.save(foreign);
    }

    private int calculateTotalScore(ForeignCalculationRequest request) {
        return calculateToeicScore(request.toeicScore()) +
                calculateToeflScore(request.toeflScore()) +
                calculateTepsScore(request.tepsScore()) +
                calculateToeicSpeakingScore(request.toeicSpeakinglevel()) +
                calculateOpicScore(request.opicScore()) +
                calculateJptScore(request.jptScore()) +
                calculateCptScore(request.cptScore()) +
                calculateHskScore(request.hskScore());
    }

}
