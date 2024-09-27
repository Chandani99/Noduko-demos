package com.noduco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.CreateTopicRequest;
import software.amazon.awssdk.services.sns.model.CreateTopicResponse;
import software.amazon.awssdk.services.sns.model.DeleteTopicRequest;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;
import software.amazon.awssdk.services.sns.model.SnsException;

@Service
public class SnsService {
	
	@Autowired
	private SnsClient snsClient;

	public String createTopic(String topicName) {
        try {
            CreateTopicRequest request = CreateTopicRequest.builder()
                    .name(topicName)
                    .attributes(null)
                    .build();

            CreateTopicResponse response = snsClient.createTopic(request);
            return response.topicArn();

        } catch (SnsException e) {
            e.printStackTrace();
            return e.awsErrorDetails().errorMessage();
        }
    }

    // Delete an SNS topic
    public void deleteTopic(String topicArn) {
        try {
            DeleteTopicRequest request = DeleteTopicRequest.builder()
                    .topicArn(topicArn)
                    .build();

            snsClient.deleteTopic(request);

        } catch (SnsException e) {
            e.printStackTrace();
        }
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
