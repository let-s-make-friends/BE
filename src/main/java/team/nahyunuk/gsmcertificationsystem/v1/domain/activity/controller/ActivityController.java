package team.nahyunuk.gsmcertificationsystem.v1.domain.activity.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.dto.request.ActivityPostRequest;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.entity.Activity;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.repository.ActivityRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.service.impl.ActivityGetServiceImpl;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.service.impl.ActivityPostServiceImpl;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.service.impl.BodyGetServiceImpl;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/activity")
public class ActivityController {

    private final ActivityPostServiceImpl activityPostService;
    private final ActivityGetServiceImpl activityGetService;
    private final BodyGetServiceImpl bodyGetService;

    @PostMapping("/post")
    public CommonApiResponse post(@Valid @RequestBody ActivityPostRequest request, @RequestHeader("Authorization") String token) {
        return activityPostService.execute(request, token);
    }

    @GetMapping
    public CommonApiResponse get(@RequestHeader("Authorization") String token) {
        return activityGetService.execute(token);
    }

    @GetMapping("/body/{activityId}")
    public CommonApiResponse getBody(@PathVariable long activityId) {
        return bodyGetService.execute(activityId);
    }
}
