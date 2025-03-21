package team.nahyunuk.gsmcertificationsystem.v1.domain.major.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import team.nahyunuk.gsmcertificationsystem.v1.domain.major.dto.request.MajorCalculationRequest;
import team.nahyunuk.gsmcertificationsystem.v1.domain.major.dto.request.MajorUpdateRequest;
import team.nahyunuk.gsmcertificationsystem.v1.domain.major.service.impl.MajorCalculationServiceImpl;
import team.nahyunuk.gsmcertificationsystem.v1.domain.major.service.impl.MajorGetServiceImpl;
import team.nahyunuk.gsmcertificationsystem.v1.domain.major.service.impl.MajorUpdateServiceImpl;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/major")
public class MajorController {

    private final MajorGetServiceImpl majorGetService;
    private final MajorCalculationServiceImpl majorCalculationService;
    private final MajorUpdateServiceImpl majorUpdateService;

    @PostMapping
    public CommonApiResponse post(@Valid @RequestBody MajorCalculationRequest request) {
        return majorCalculationService.execute(request);
    }

    @GetMapping
    public CommonApiResponse get() {
        return majorGetService.execute();
    }

    @PostMapping("/update")
    public CommonApiResponse update(@Valid @RequestBody MajorUpdateRequest request) {
        return majorUpdateService.execute(request);
    }
}
