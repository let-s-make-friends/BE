package team.nahyunuk.gsmcertificationsystem.v1.domain.auth.service;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import team.nahyunuk.gsmcertificationsystem.v1.global.security.jwt.dto.TokenDto;

public interface SignInService {
    ResponseEntity<TokenDto> execute(@Valid Sign);
}
