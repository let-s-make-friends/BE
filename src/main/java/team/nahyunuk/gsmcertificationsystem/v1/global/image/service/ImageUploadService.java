package team.nahyunuk.gsmcertificationsystem.v1.global.image.service;

import org.springframework.web.multipart.MultipartFile;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;

public interface ImageUploadService {
    CommonApiResponse execute(MultipartFile image);
}
