package team.nahyunuk.gsmcertificationsystem.v1.domain.student.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.dto.response.GetTotalResponse;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.entity.Student;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.repository.StudentRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.service.GetTotalScoreService;
import team.nahyunuk.gsmcertificationsystem.v1.domain.user.entity.User;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.CustomException;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.error.ErrorCode;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;
import team.nahyunuk.gsmcertificationsystem.v1.global.security.jwt.TokenProvider;

@Service
@RequiredArgsConstructor
public class GetTotalScoreServiceImpl implements GetTotalScoreService {

    private final StudentRepository studentRepository;
    private final TokenProvider tokenProvider;

    @Override
    @Transactional(readOnly = true)
    public CommonApiResponse execute(String token) {
        User user = tokenProvider.findUserByToken(token);
        Student student = studentRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.STUDENT_NOT_FOUND));

        return CommonApiResponse.successWithData(null, new GetTotalResponse(student.getTotalScore()));
    }
}
