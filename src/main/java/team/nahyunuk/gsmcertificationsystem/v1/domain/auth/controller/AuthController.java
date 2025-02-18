package team.nahyunuk.gsmcertificationsystem.v1.domain.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import team.nahyunuk.gsmcertificationsystem.v1.domain.auth.dto.request.SendMailRequest;
import team.nahyunuk.gsmcertificationsystem.v1.domain.auth.dto.request.SignInRequest;
import team.nahyunuk.gsmcertificationsystem.v1.domain.auth.dto.request.SignUpRequest;
import team.nahyunuk.gsmcertificationsystem.v1.domain.auth.dto.request.VerifyCodeRequest;
import team.nahyunuk.gsmcertificationsystem.v1.domain.auth.service.impl.*;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;
import team.nahyunuk.gsmcertificationsystem.v1.global.security.jwt.dto.TokenDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final ReissueTokenServiceImpl reissueTokenService;
    private final SendMailServiceImpl sendMailService;
    private final SignUpServiceImpl signUpService;
    private final VerifyCodeServiceImpl verifyCodeService;
    private final SignInServiceImpl signInService;
    private final LogOutServiceImpl logOutService;

    @PatchMapping("/reissue-token")
    public CommonApiResponse<TokenDto> reissueToken(@RequestHeader("Authorization") String token) {
        return reissueTokenService.execute(token);
    }

    @PostMapping("/send-mail")
    public CommonApiResponse sendMail(@Valid @RequestBody SendMailRequest request) {
        return sendMailService.execute(request);
    }

    @PostMapping("/verify-code")
    public CommonApiResponse verifyCode(@Valid @RequestBody VerifyCodeRequest request) {
        return verifyCodeService.execute(request);
    }

    @PostMapping("/sign-up")
    public CommonApiResponse signUp(@Valid @RequestBody SignUpRequest request) {
        return signUpService.execute(request);
    }

    @PostMapping("/sign-in")
    public CommonApiResponse signIn(@Valid @RequestBody SignInRequest request) {
        return signInService.execute(request);
    }

    @DeleteMapping("/logout")
    public CommonApiResponse logout(@RequestHeader("Authorization") String token) {
        return logOutService.execute(token);
    }
}
