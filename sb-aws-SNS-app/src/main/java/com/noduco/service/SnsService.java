package com.noduco.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;
import software.amazon.awssdk.services.sns.model.SnsException;

@Service
public class SnsService {
	
	private final SnsClient snsClient;

    public SnsService( @Value("${aws.accessKeyId}") String accessKeyId,
            @Value("${aws.secretKey}") String secretKey,
            @Value("${aws.region}") String region)  {
    	

     this.snsClient = SnsClient.builder()
             .region(Region.of(region))
             .credentialsProvider(StaticCredentialsProvider.create(
                     AwsBasicCredentials.create(accessKeyId, secretKey)))
             .build();
         
    }

    public String publishMessage(String message, String topicArn) {
        try {
            PublishRequest request = PublishRequest.builder()
                    .message(message)
                    .topicArn(topicArn)
                    .build();

            PublishResponse response = snsClient.publish(request);

            return response.messageId();

        } catch (SnsException e) {
            e.printStackTrace();
            return e.awsErrorDetails().errorMessage();
        }
    }

}
