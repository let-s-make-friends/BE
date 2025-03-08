package team.nahyunuk.gsmcertificationsystem.v1.global.image.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import team.nahyunuk.gsmcertificationsystem.v1.global.image.pdf.service.impl.SavePdfServiceImpl;
import team.nahyunuk.gsmcertificationsystem.v1.global.image.service.impl.ImageUploadServiceImpl;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/image")
public class ImageController {
    private final ImageUploadServiceImpl imageUploadService;
    private final SavePdfServiceImpl savePdfService;

    @PostMapping
    public CommonApiResponse uploadImage(@RequestPart MultipartFile image) {
        return imageUploadService.execute(image);
    }

    @PostMapping("/pdf")
    public CommonApiResponse uploadPdf(@RequestPart MultipartFile pdf) {
        return savePdfService.execute(pdf);
    }

}
