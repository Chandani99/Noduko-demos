package com.noduco.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.CreateTopicRequest;
import software.amazon.awssdk.services.sns.model.CreateTopicResponse;
import software.amazon.awssdk.services.sns.model.DeleteTopicRequest;
import software.amazon.awssdk.services.sns.model.SnsException;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.CreateQueueRequest;
import software.amazon.awssdk.services.sqs.model.CreateQueueResponse;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.DeleteQueueRequest;
import software.amazon.awssdk.services.sqs.model.ListQueuesRequest;
import software.amazon.awssdk.services.sqs.model.ListQueuesResponse;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageResponse;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;

@Service
public class SqsService {
	
	@Autowired
	private SqsClient sqsClient;
	
	@Autowired
	private SnsClient snsClient;
	
	@Value("${aws.sqs.queueUrl}")
    private String queueUrl;
	
	// Create an SQS queue
    public String createQueue(String queueName) {
        CreateQueueRequest createQueueRequest = CreateQueueRequest.builder()
                .queueName(queueName)
                .attributes(null)
                .build();

        CreateQueueResponse createQueueResponse = sqsClient.createQueue(createQueueRequest);
        return createQueueResponse.queueUrl();
    }
    
    // Delete an SQS queue
    public void deleteQueue(String queueUrl) {
        DeleteQueueRequest deleteQueueRequest = DeleteQueueRequest.builder()
                .queueUrl(queueUrl)
                .build();

        sqsClient.deleteQueue(deleteQueueRequest);
    }
    
 // Create an SNS topic
    public String createTopic(String topicName) {
        try {
            CreateTopicRequest request = CreateTopicRequest.builder()
                    .name(topicName)
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

    // Send message to SQS
    public String sendMessage(String messageBody) {
        SendMessageRequest request = SendMessageRequest.builder()
                .queueUrl(queueUrl)
                .messageBody(messageBody)
                .build();

        SendMessageResponse response = sqsClient.sendMessage(request);
        return response.messageId();
    }

    // Receive messages from SQS
    public List<Message> receiveMessages() {
        ReceiveMessageRequest request = ReceiveMessageRequest.builder()
                .queueUrl(queueUrl)
                .maxNumberOfMessages(5)
                .build();

        ReceiveMessageResponse response = sqsClient.receiveMessage(request);
        return response.messages();
    }

    // Delete message from SQS
    public void deleteMessage(String receiptHandle) {
        DeleteMessageRequest deleteRequest = DeleteMessageRequest.builder()
                .queueUrl(queueUrl)
                .receiptHandle(receiptHandle)
                .build();
        sqsClient.deleteMessage(deleteRequest);
    }

    // List all SQS queues
    public List<String> listQueues() {
        ListQueuesRequest listQueuesRequest = ListQueuesRequest.builder().build();
        ListQueuesResponse listQueuesResponse = sqsClient.listQueues(listQueuesRequest);
        return listQueuesResponse.queueUrls();
    }

}
