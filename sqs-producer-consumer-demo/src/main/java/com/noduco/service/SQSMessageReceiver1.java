package com.noduco.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sqs.AmazonSQSAsync;

import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;


@Service
public class SQSMessageReceiver1  {

	 @Autowired
		private SqsClient sqsClient;
	    
	    @Value("${aws.sqs.queue.url}")
	    private String queueUrl ;
	    
	    @Scheduled(fixedDelay = 5000)  // Poll every 5 seconds
	    public void consumeMessages() {
	    	
	        ReceiveMessageRequest receiveRequest = ReceiveMessageRequest.builder()
	                .queueUrl(queueUrl)
	                .maxNumberOfMessages(10)
	                .build();

	        List<Message> messages = sqsClient.receiveMessage(receiveRequest).messages();

	        for (Message message : messages) {
	            System.out.println("Message received: " + message.body());
	            // Simulate message processing
	            // call method to process the message
    
	            // Acknowledge (delete) the message after processing
                acknowledgeMessage(message);

	        }
	        
	        
	    }
	    private void acknowledgeMessage(Message message) {
	        DeleteMessageRequest deleteRequest = DeleteMessageRequest.builder()
	                .queueUrl(queueUrl)
	                .receiptHandle(message.receiptHandle())
	                .build();
	        sqsClient.deleteMessage(deleteRequest);
	        System.out.println("Acknowledged message: " + message.body());
	    }
	    
	    
	    
}
