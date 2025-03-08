package team.nahyunuk.gsmcertificationsystem.v1.global.thirdParty.aws.util;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.CustomException;
import team.nahyunuk.gsmcertificationsystem.v1.global.exception.error.ErrorCode;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class S3Util {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 s3;

    public String imageUpload(MultipartFile image) {
        try {
            List<String> list = List.of("jpg", "jpeg", "png");

            String[] split = image.getOriginalFilename().split("\\.");

            if (split.length < 2) {
                throw new CustomException(ErrorCode.FILE_EXTENSION_INVALID);
            }

            String extension = split[1].toLowerCase();

            if (list.stream().noneMatch(it -> it.equals(extension))) {
                throw new CustomException(ErrorCode.FILE_EXTENSION_INVALID);
            }

            return upload(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String upload(MultipartFile image) throws IOException {
        String profileName = bucket + "/" + UUID.randomUUID() + image.getOriginalFilename();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(image.getInputStream().available());
        s3.putObject(bucket, profileName, image.getInputStream(), metadata);
        return s3.getUrl(bucket, profileName).toString();
    }

    public void deleteImage(String url) {
        String key = url.split("com/")[1];
        s3.deleteObject(bucket, key);
    }

    public String pdfUpload(MultipartFile pdf) {
        try {
            if (!pdf.getOriginalFilename().endsWith(".pdf")) {
                throw new CustomException(ErrorCode.FILE_EXTENSION_INVALID);
            }

            return upload(pdf);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
