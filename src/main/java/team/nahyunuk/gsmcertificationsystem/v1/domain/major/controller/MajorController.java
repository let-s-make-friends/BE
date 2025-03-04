package team.nahyunuk.gsmcertificationsystem.v1.domain.major.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import team.nahyunuk.gsmcertificationsystem.v1.domain.major.dto.request.MajorCalculationRequest;
import team.nahyunuk.gsmcertificationsystem.v1.domain.major.service.impl.MajorCalculationServiceImpl;
import team.nahyunuk.gsmcertificationsystem.v1.domain.major.service.impl.MajorGetServiceImpl;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/major")
public class MajorController {

    private final MajorGetServiceImpl majorGetService;
    private final MajorCalculationServiceImpl majorCalculationService;

    @PostMapping
    public CommonApiResponse post(@Valid @RequestBody MajorCalculationRequest request, @RequestHeader("Authorization") String token) {
        return majorCalculationService.execute(token, request);
    }

    @GetMapping
    public CommonApiResponse get(@RequestHeader("Authorization") String token) {
        return majorGetService.execute(token);
    }
}
