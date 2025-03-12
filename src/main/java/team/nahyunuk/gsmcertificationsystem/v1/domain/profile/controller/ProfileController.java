package team.nahyunuk.gsmcertificationsystem.v1.domain.profile.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import team.nahyunuk.gsmcertificationsystem.v1.domain.profile.dto.request.ProfileUpdateRequest;
import team.nahyunuk.gsmcertificationsystem.v1.domain.profile.service.impl.ProfileGetServiceImpl;
import team.nahyunuk.gsmcertificationsystem.v1.domain.profile.service.impl.ProfileUpdateServiceImpl;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileGetServiceImpl profileGetService;
    private final ProfileUpdateServiceImpl profileUpdateService;

    @PostMapping
    public CommonApiResponse update(@RequestBody ProfileUpdateRequest request) {
        return profileUpdateService.execute(request);
    }

    @GetMapping
    public CommonApiResponse get() {
        return profileGetService.execute();
    }
}
