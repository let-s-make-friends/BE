package team.nahyunuk.gsmcertificationsystem.v1.domain.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.repository.StudentRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.user.dto.request.ChangeUserRoleRequest;
import team.nahyunuk.gsmcertificationsystem.v1.domain.user.entity.User;
import team.nahyunuk.gsmcertificationsystem.v1.domain.user.repository.UserRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.user.service.ChangeUserRoleService;
import team.nahyunuk.gsmcertificationsystem.v1.domain.user.type.Authority;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.CustomException;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.error.ErrorCode;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;
import team.nahyunuk.gsmcertificationsystem.v1.global.security.jwt.TokenProvider;
import team.nahyunuk.gsmcertificationsystem.v1.domain.user.type.Authority;
import team.nahyunuk.gsmcertificationsystem.v1.global.util.UserUtil;

@Service
@RequiredArgsConstructor
public class ChangeUserRoleServiceImpl implements ChangeUserRoleService {

    private final UserRepository userRepository;
    private final UserUtil userUtil;

    @Override
    @Transactional
    public CommonApiResponse execute(ChangeUserRoleRequest request) {
        User adminUser = userUtil.getCurrentUser();
        validateUserPermission(adminUser);

        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        user.changeAuthority(request.authority());
        return CommonApiResponse.success("권한이 변경되었습니다.");
    }

    private void validateUserPermission(User user) {
        if (user.getAuthority() == Authority.ADMIN || user.getAuthority() == Authority.TEACHER) {
            throw new CustomException(ErrorCode.FORBIDDEN_ACCESS);
        }
    }

}
