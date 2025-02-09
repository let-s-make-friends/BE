package team.nahyunuk.gsmcertificationsystemv1.global.security.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import team.nahyunuk.gsmcertificationsystemv1.domain.user.repository.UserRepository;
import team.nahyunuk.gsmcertificationsystemv1.global.exception.CustomException;
import team.nahyunuk.gsmcertificationsystemv1.global.exception.error.ErrorCode;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        return new CustomUserDetails(userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND)));
    }
}
