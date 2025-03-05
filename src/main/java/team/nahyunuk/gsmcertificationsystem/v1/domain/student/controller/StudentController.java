package team.nahyunuk.gsmcertificationsystem.v1.domain.student.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.service.impl.StudentUploadServiceImpl;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("admin/student")
public class StudentController {

    private final StudentUploadServiceImpl studentUploadService;

    @PostMapping
    public CommonApiResponse upload(MultipartFile file,
                                    @RequestHeader("Authorization") String token) {
        return studentUploadService.execute(file, token);
    }
}
