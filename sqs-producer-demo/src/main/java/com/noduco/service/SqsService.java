package com.noduco.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.CreateQueueRequest;
import software.amazon.awssdk.services.sqs.model.CreateQueueResponse;
import software.amazon.awssdk.services.sqs.model.DeleteQueueRequest;
import software.amazon.awssdk.services.sqs.model.QueueAttributeName;
@Service
public class SqsService {
	
	@Autowired
	private SqsClient sqsClient;
	@Autowired
    private QueueMessagingTemplate queueMessagingTemplate;
	
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

//    To create FIFO Queue wit attributes
    public String createFIFOQueue(String queueName) {
    	
            queueName += ".fifo";
        
    	
//    	System.out.println("======="+queueName);
    	
        Map<QueueAttributeName, String> attributes = new HashMap<>();
        
        attributes.put(QueueAttributeName.FIFO_QUEUE, "true");
        attributes.put(QueueAttributeName.CONTENT_BASED_DEDUPLICATION, "true");
        attributes.put(QueueAttributeName.VISIBILITY_TIMEOUT, "60");
        attributes.put(QueueAttributeName.DELAY_SECONDS, "10");
        attributes.put(QueueAttributeName.MESSAGE_RETENTION_PERIOD, "86400");// Optional attributes

        CreateQueueRequest createQueueRequest = CreateQueueRequest.builder()
                .queueName(queueName)
                .attributes(attributes)
                .build();

        CreateQueueResponse createQueueResponse = sqsClient.createQueue(createQueueRequest);
        return createQueueResponse.queueUrl();
    }

	    public String sendMessage(String queueUrl, String message) {
	        queueMessagingTemplate.convertAndSend(queueUrl, message);
	       return "Message sent to SQS: " + message;
	    }
	    
	    public String sendMessage2(String queueUrl, String message) {
	    	Message payload = MessageBuilder.withPayload(message)
	    			.setHeader("Sender", "Chandani")
	    			.build();
	        queueMessagingTemplate.send(message, payload);;
	       return "Message sent to SQS: " + message;
	    }
}
