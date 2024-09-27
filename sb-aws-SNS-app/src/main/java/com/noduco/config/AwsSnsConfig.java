package com.noduco.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;

@Configuration
public class AwsSnsConfig {
	
	@Bean
	public SnsClient returnSnsClient(@Value("${aws.accessKeyId}") String accessKeyId,
            @Value("${aws.secretKey}") String secretKey,
            @Value("${aws.region}") String region) {
		return SnsClient.builder()
	             .region(Region.of(region))
	             .credentialsProvider(StaticCredentialsProvider.create(
	                     AwsBasicCredentials.create(accessKeyId, secretKey)))
	             .build();
	}

}
