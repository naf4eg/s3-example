package com.bachev.s3.example.s3_example;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;

@Service
@Slf4j
public class AmazonS3ClientService {

    @Autowired
    private AmazonS3 clientS3;
    @Autowired
    private AmazonS3ClientConfigProp amazonS3ClientConfigProp;
    @Autowired
    private ReportService reportService;

    public void putTestImage() throws FileNotFoundException {
        var bucketName = amazonS3ClientConfigProp.getBucketName();
        var fileName = amazonS3ClientConfigProp.getFileImageName();
        createBucketDoesExist(bucketName);
        var file = ResourceUtils.getFile("classpath:image.jpg");
        clientS3.putObject(bucketName, fileName, file);
        log.info("==>> image:{} put to bucket:{}", fileName, bucketName);
    }

    public void putReport() throws Exception {
        var reportAsByte = reportService.getReportAsByte();
        var byteArrayInputStream = new ByteArrayInputStream(reportAsByte);
        var bucketName = amazonS3ClientConfigProp.getBucketName();
        var fileName = amazonS3ClientConfigProp.getFileReportName();
        createBucketDoesExist(bucketName);
        var objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(reportAsByte.length);
        clientS3.putObject(bucketName, fileName, byteArrayInputStream, objectMetadata);
        log.info("==>> report:{} put to bucket:{}", fileName, bucketName);
    }

    private void createBucketDoesExist(String bucketName) {
        if (!clientS3.doesBucketExistV2(bucketName)) {
            clientS3.createBucket(bucketName);
            log.info("==> Bucket \"{}\" created", bucketName);
        } else {
            log.info("==> Bucket: \"{}\" exist", bucketName);
        }
    }
}
