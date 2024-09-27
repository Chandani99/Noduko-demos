package com.noduco.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;

import software.amazon.awssdk.services.sqs.SqsClient;

@Configuration
public class AwsSqsConfiguration {
//	private String accessKey;
//	private String secrateKey;
	
	
//	@Bean
//	public QueueMessagingTemplate mqt(AmazonSQSAsync asa) {
//		return new QueueMessagingTemplate(asa());
//	}
//
//
//	private AmazonSQSAsync asa() {
//		return null;
//		// TODO Auto-generated method stub
//		
//	}
	
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

	   
  
	
}
