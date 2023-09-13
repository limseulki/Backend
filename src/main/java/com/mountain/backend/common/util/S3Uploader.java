package com.mountain.backend.common.util;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Uploader {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    // S3에 파일 업로드하고, 업로드된 파일 url 반환
    public String upload(MultipartFile multipartFile) throws IOException {
        String fileName = UUID.randomUUID().toString() + "_" + multipartFile.getOriginalFilename();

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(multipartFile.getSize());

        InputStream inputStream = multipartFile.getInputStream();

        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
                .withCannedAcl(CannedAccessControlList.PublicRead));

        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

    // S3에서 파일 삭제 (soft Delete라 삭제하지 않고 유지)
//    public boolean remove(String fileUrl) {
//        try {
//            amazonS3Client.deleteObject(new DeleteObjectRequest(bucket, fileUrl));
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }

}
