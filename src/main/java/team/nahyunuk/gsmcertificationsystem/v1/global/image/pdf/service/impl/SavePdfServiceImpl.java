package team.nahyunuk.gsmcertificationsystem.v1.global.image.pdf.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import team.nahyunuk.gsmcertificationsystem.v1.global.image.pdf.service.SavePdfService;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;
import team.nahyunuk.gsmcertificationsystem.v1.global.thirdParty.aws.util.S3Util;

@Service
@RequiredArgsConstructor
public class SavePdfServiceImpl implements SavePdfService {

    private final S3Util s3Util;

    @Override
    public CommonApiResponse execute(MultipartFile pdf) {
        String pdfUrl = s3Util.pdfUpload(pdf);
        return CommonApiResponse.successWithData("pdf가 저장되었습니다.", pdfUrl);
    }

}
