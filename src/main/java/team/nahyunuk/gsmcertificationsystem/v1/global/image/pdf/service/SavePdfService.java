package team.nahyunuk.gsmcertificationsystem.v1.global.image.pdf.service;

import org.springframework.web.multipart.MultipartFile;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;

public interface SavePdfService {
    CommonApiResponse execute(MultipartFile pdf) throws Exception;
}
