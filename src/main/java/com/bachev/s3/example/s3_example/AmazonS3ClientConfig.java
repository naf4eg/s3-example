package com.bachev.s3.example.s3_example;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonS3ClientConfig {

    @Bean
    public AmazonS3 clientS3(AmazonS3ClientConfigProp amazonS3ClientConfigProp) {
        System.setProperty("aws.java.v1.disableDeprecationAnnouncement", "true");

        var accessKeyId = amazonS3ClientConfigProp.getAccessKeyId();
        var secretAccessKey = amazonS3ClientConfigProp.getSecretAccessKey();
        var serviceEndpoint = amazonS3ClientConfigProp.getServiceEndpoint();


        var client = AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
                        serviceEndpoint,
                        null
                ))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKeyId, secretAccessKey)))
                .build();

        return client;
    }
}
