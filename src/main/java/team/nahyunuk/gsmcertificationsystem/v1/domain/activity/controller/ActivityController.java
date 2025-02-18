package team.nahyunuk.gsmcertificationsystem.v1.domain.activity.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.dto.request.ActivityPostRequest;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.entity.Activity;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.repository.ActivityRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.service.impl.ActivityPostServiceImpl;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/activity")
public class ActivityController {

    private final ActivityPostServiceImpl activityPostService;

    @PostMapping("/post")
    public CommonApiResponse post(@Valid @RequestBody ActivityPostRequest request, @RequestHeader("Authorization") String token) {
        return activityPostService.execute(request, token);
    }
}
