package team.nahyunuk.gsmcertificationsystem.v1.domain.foreign.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.nahyunuk.gsmcertificationsystem.v1.domain.foreign.dto.request.ForeignCalculationRequest;
import team.nahyunuk.gsmcertificationsystem.v1.domain.foreign.entity.Foreign;
import team.nahyunuk.gsmcertificationsystem.v1.domain.foreign.repository.ForeignRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.foreign.service.ForeignCalculationService;
import team.nahyunuk.gsmcertificationsystem.v1.domain.foreign.service.ForeignUpdateService;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.entity.Student;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.repository.StudentRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.user.entity.User;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.CustomException;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.error.ErrorCode;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;
import team.nahyunuk.gsmcertificationsystem.v1.global.util.UserUtil;

@Service
@RequiredArgsConstructor
public class ForeignUpdateServiceImpl implements ForeignUpdateService {

    private final StudentRepository studentRepository;
    private final UserUtil userUtil;
    private final ForeignRepository foreignRepository;
    private final ForeignCalculationService foreignCalculationService;

    @Override
    @Transactional
    public CommonApiResponse execute(ForeignCalculationRequest request) {
        Student student = getStudent();
        Foreign foreign = foreignRepository.findByStudent(student);

        foreign.update(request);
        return CommonApiResponse.success("외국어 영역이 저장되었습니다.");
    }

    private Student getStudent() {
        User user = userUtil.getCurrentUser();
        return studentRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.STUDENT_NOT_FOUND));
    }
}
