package team.nahyunuk.gsmcertificationsystem.v1.domain.foreign.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.nahyunuk.gsmcertificationsystem.v1.domain.foreign.convert.ForeignConvert;
import team.nahyunuk.gsmcertificationsystem.v1.domain.foreign.dto.response.ForeignGetResponse;
import team.nahyunuk.gsmcertificationsystem.v1.domain.foreign.entity.Foreign;
import team.nahyunuk.gsmcertificationsystem.v1.domain.foreign.repository.ForeignRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.foreign.service.ForeignGetService;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.entity.Student;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.repository.StudentRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.user.entity.User;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.CustomException;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.error.ErrorCode;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;
import team.nahyunuk.gsmcertificationsystem.v1.global.util.UserUtil;

@Service
@RequiredArgsConstructor
public class ForeignGetServiceImpl implements ForeignGetService {

    private final ForeignConvert foreignConvert;
    private final UserUtil userUtil;
    private final StudentRepository studentRepository;
    private final ForeignRepository foreignRepository;

    @Override
    @Transactional(readOnly = true)
    public CommonApiResponse execute() {
        Student student = getStudent();
        Foreign foreign = getForeignByStudent(student);
        ForeignGetResponse foreignGetResponse = foreignConvert.getResponse(foreign);
        return CommonApiResponse.successWithData(null, foreignGetResponse);
    }

    private Student getStudent() {
        User user = userUtil.getCurrentUser();
        return studentRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.STUDENT_NOT_FOUND));
    }

    private Foreign getForeignByStudent(Student student) {
        return foreignRepository.findByStudent(student);
    }
}
