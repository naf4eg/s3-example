package com.bachev.s3.example.s3_example;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ResourceUtils;

import javax.sql.DataSource;
import java.io.FileNotFoundException;

@SpringBootApplication
@Slf4j
@RequiredArgsConstructor
public class S3ExampleApplication {

	public static void main(String[] args) throws Exception {
		var configurableApplicationContext = SpringApplication.run(S3ExampleApplication.class, args);

		var amazonS3ClientService = configurableApplicationContext.getBean("amazonS3ClientService", AmazonS3ClientService.class);

		amazonS3ClientService.putTestImage();
		amazonS3ClientService.putReport();
	}
}
