package team.nahyunuk.gsmcertificationsystem.v1.domain.major.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.nahyunuk.gsmcertificationsystem.v1.domain.major.repository.MajorRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.repository.StudentRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.user.repository.UserRepository;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;
import team.nahyunuk.gsmcertificationsystem.v1.global.security.jwt.TokenProvider;

@Service
@RequiredArgsConstructor
public class MajorGetServiceImpl {

    private final UserRepository userRepository;
    private final MajorRepository majorRepository;
    private final StudentRepository studentRepository;
    private final TokenProvider tokenProvider;

    @Override
    @Transactional(readOnly = true)
    public CommonApiResponse execute(String token) {
        return CommonApiResponse.successWithData(null, )
    }
}
