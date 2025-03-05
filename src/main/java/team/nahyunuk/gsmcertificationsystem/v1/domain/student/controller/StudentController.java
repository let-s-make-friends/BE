package team.nahyunuk.gsmcertificationsystem.v1.domain.student.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.service.GetTotalScoreService;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.service.impl.GetTotalScoreServiceImpl;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.service.impl.StudentUploadServiceImpl;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("admin/student")
public class StudentController {

    private final StudentUploadServiceImpl studentUploadService;
    private final GetTotalScoreServiceImpl getTotalScoreService;

    @PostMapping
    public CommonApiResponse upload(MultipartFile file,
                                    @RequestHeader("Authorization") String token) {
        return studentUploadService.execute(file, token);
    }

    @GetMapping("score")
    public CommonApiResponse score(@RequestHeader("Authorization") String token) {
        return getTotalScoreService.execute(token);
    }
}
