package team.nahyunuk.gsmcertificationsystem.v1.domain.student.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
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
    public CommonApiResponse upload(MultipartFile file) {
        return studentUploadService.execute(file);
    }

    @GetMapping("score")
    public CommonApiResponse score() {
        return getTotalScoreService.execute();
    }
}
