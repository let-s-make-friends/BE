package team.nahyunuk.gsmcertificationsystem.v1.domain.major.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.nahyunuk.gsmcertificationsystem.v1.domain.major.dto.request.MajorUpdateRequest;
import team.nahyunuk.gsmcertificationsystem.v1.domain.major.entity.Major;
import team.nahyunuk.gsmcertificationsystem.v1.domain.major.repository.MajorRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.major.service.MajorUpdateService;
import team.nahyunuk.gsmcertificationsystem.v1.domain.user.entity.User;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.CustomException;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.error.ErrorCode;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;
import team.nahyunuk.gsmcertificationsystem.v1.global.security.jwt.TokenProvider;

@Service
@RequiredArgsConstructor
public class MajorUpdateServiceImpl implements MajorUpdateService {

    private final MajorRepository majorRepository;
    private final TokenProvider tokenProvider;

    @Override
    @Transactional
    public CommonApiResponse execute(MajorUpdateRequest request, String token) {
        User user = tokenProvider.findUserByToken(token);
        Major major = majorRepository.findById(request.id())
                .orElseThrow(() -> new CustomException(ErrorCode.MAJOR_NOT_FOUND));

        if (!major.getStudent().getEmail().equals(user.getEmail())) {
            throw new CustomException(ErrorCode.FORBIDDEN_ACCESS);
        }

        major.update(request);
        return CommonApiResponse.success("전공 영역이 저장되었습니다.");
    }
}
