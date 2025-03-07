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

@Service
@RequiredArgsConstructor
public class ChangeUserRoleServiceImpl implements ChangeUserRoleService {

    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;

    @Override
    @Transactional
    public CommonApiResponse execute(ChangeUserRoleRequest request, String token) {
        User adminUser = findUserByToken(token);
        validateUserPermission(adminUser);

        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        user.changeAuthority(request.authority());
        return CommonApiResponse.success("권한이 변경되었습니다.");
    }

    private User findUserByToken(String token) {
        String removeToken = tokenProvider.removePrefix(token);
        String userId = tokenProvider.getUserIdFromAccessToken(removeToken);
        return userRepository.findByUserId(Long.valueOf(userId));
    }

    private void validateUserPermission(User user) {
        if (user.getAuthority() == Authority.ADMIN || user.getAuthority() == Authority.TEACHER) {
            throw new CustomException(ErrorCode.FORBIDDEN_ACCESS);
        }
    }

}
