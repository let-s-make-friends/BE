package team.nahyunuk.gsmcertificationsystem.v1.global.image.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import team.nahyunuk.gsmcertificationsystem.v1.global.image.service.ImageUploadService;
import team.nahyunuk.gsmcertificationsystem.v1.global.response.CommonApiResponse;
import team.nahyunuk.gsmcertificationsystem.v1.global.thirdParty.aws.util.S3Util;

@Service
@RequiredArgsConstructor
public class ImageUploadServiceImpl implements ImageUploadService {

    private final S3Util s3Util;

    @Override
    public CommonApiResponse execute(MultipartFile image) {
        String imageUrl = s3Util.imageUpload(image);
        return CommonApiResponse.successWithData("이미지가 저장되었습니다", imageUrl);
    }
}
