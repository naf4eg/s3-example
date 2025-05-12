package com.bachev.s3.example.s3_example;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.amazon-s3-client")
@Getter
@Setter
@NoArgsConstructor
public class AmazonS3ClientConfigProp {
    private String accessKeyId;
    private String secretAccessKey;
    private String bucketName;
    private String fileImageName;
    private String fileReportName;
    private String serviceEndpoint;
}
