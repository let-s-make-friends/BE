package team.nahyunuk.gsmcertificationsystem.v1.global.util;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import team.nahyunuk.gsmcertificationsystem.v1.domain.user.entity.User;
import team.nahyunuk.gsmcertificationsystem.v1.domain.user.repository.UserRepository;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.CustomException;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.error.ErrorCode;
import team.nahyunuk.gsmcertificationsystem.v1.global.security.auth.CustomUserDetails;

@Component
@RequiredArgsConstructor
public class UserUtil {

    private final UserRepository userRepository;

    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof CustomUserDetails) {
            String userId = ((CustomUserDetails) principal).getUsername();
            return userRepository.findById(Long.valueOf(userId))
                    .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        } else {
            throw new CustomException(ErrorCode.PRINCIPAL_INVALID);
        }

    }
}
