package com.noduco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Service;

import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.CreateQueueRequest;
import software.amazon.awssdk.services.sqs.model.CreateQueueResponse;
import software.amazon.awssdk.services.sqs.model.DeleteQueueRequest;

@Service
public class SqsConsumerService1 {
	
	@SqsListener("your-queue-name")
    public void consumeMessage(String message) {
        System.out.println("Consumer 1 received message: " + message);
    }
}
