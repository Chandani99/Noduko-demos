package com.noduco.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AwsS3Config {
	@Value("${aws.accessKeyId}")
	private String accessKey;
	
	@Value("${aws.secretKey}")
	private String secrateKey;
	
	@Value("${aws.region}")
	private String region;
	
	
	public AWSCredentials credentials() {
		AWSCredentials credientials = new BasicAWSCredentials(accessKey, secrateKey);
		return credientials;
	}
	
	@Bean
	public AmazonS3 amazonS3() {
		AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials())).withRegion(region)
				.build();
		return s3Client;
		
	}

}
