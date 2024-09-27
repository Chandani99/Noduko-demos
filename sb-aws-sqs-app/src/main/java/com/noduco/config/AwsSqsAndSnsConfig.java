package com.noduco.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sqs.SqsClient;

@Configuration
public class AwsSqsAndSnsConfig {

   
    
    @Bean
    public SqsClient sqsClient(@Value("${aws.accessKeyId}") String accessKeyId,
            @Value("${aws.secretKey}") String secretKey,
            @Value("${aws.region}") String region) {
    	
    	return SqsClient.builder()
        .region(Region.US_EAST_1)  // Update region
        .credentialsProvider(StaticCredentialsProvider.create(
                AwsBasicCredentials.create(accessKeyId, secretKey)))
        .build();
    	
    }

    public SnsClient getSnsClient(@Value("${aws.accessKeyId}") String accessKeyId,
            @Value("${aws.secretKey}") String secretKey,
            @Value("${aws.region}") String region) {
        return SnsClient.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(accessKeyId, secretKey)))
                .build();
    }

}
