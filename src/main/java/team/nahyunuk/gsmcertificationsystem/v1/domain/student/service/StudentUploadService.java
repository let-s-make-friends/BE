package team.nahyunuk.gsmcertificationsystem.v1.domain.student.service;

import org.springframework.web.multipart.MultipartFile;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;

import java.io.IOException;

public interface StudentUploadService {
    CommonApiResponse execute(MultipartFile file) throws IOException;
}
