package team.nahyunuk.gsmcertificationsystem.v1.domain.major.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.nahyunuk.gsmcertificationsystem.v1.domain.major.convert.MajorConvert;
import team.nahyunuk.gsmcertificationsystem.v1.domain.major.dto.response.MajorGetResponse;
import team.nahyunuk.gsmcertificationsystem.v1.domain.major.entity.Major;
import team.nahyunuk.gsmcertificationsystem.v1.domain.major.repository.MajorRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.major.service.MajorGetService;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.entity.Student;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.repository.StudentRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.user.entity.User;
import team.nahyunuk.gsmcertificationsystem.v1.domain.user.repository.UserRepository;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;
import team.nahyunuk.gsmcertificationsystem.v1.global.security.jwt.TokenProvider;

@Service
@RequiredArgsConstructor
public class MajorGetServiceImpl implements MajorGetService {

    private final UserRepository userRepository;
    private final MajorRepository majorRepository;
    private final StudentRepository studentRepository;
    private final TokenProvider tokenProvider;
    private final MajorConvert majorConvert;

    @Override
    @Transactional(readOnly = true)
    public CommonApiResponse execute(String token) {
        User user = findUserByToken(token);
        Student student = studentRepository.findByEmail(user.getEmail());
        Major major = majorRepository.findByStudent(student);
        return CommonApiResponse.successWithData(null, majorConvert.getMajor(major));
    }

    private User findUserByToken(String token) {
        String removeToken = tokenProvider.removePrefix(token);
        String userId = tokenProvider.getUserIdFromAccessToken(removeToken);
        return userRepository.findByUserId(Long.valueOf(userId));
    }
}
