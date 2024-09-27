package com.noduco.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.noduco.service.SqsService;

import software.amazon.awssdk.services.sqs.model.Message;

public class SQSController {

	@Autowired
    private SqsService awsService;

    // Create an SQS queue
    @PostMapping("/queue/create")
    public String createQueue(@RequestParam String queueName) {
        return awsService.createQueue(queueName);
    }

    // Delete an SQS queue
    @DeleteMapping("/queue/delete")
    public void deleteQueue(@RequestParam String queueUrl) {
        awsService.deleteQueue(queueUrl);
    }

    // Create an SNS topic
    @PostMapping("/topic/create")
    public String createTopic(@RequestParam String topicName) {
        return awsService.createTopic(topicName);
    }

    // Delete an SNS topic
    @DeleteMapping("/topic/delete")
    public void deleteTopic(@RequestParam String topicArn) {
        awsService.deleteTopic(topicArn);
    }

    // Send a message to SQS queue
    @PostMapping("/sqs/send")
    public String sendMessage(@RequestParam String message) {
        return awsService.sendMessage(message);
    }

    // Receive messages from the SQS queue
    @GetMapping("/sqs/receive")
    public List<Message> receiveMessages() {
        return awsService.receiveMessages();
    }

    // Delete message from SQS queue
    @DeleteMapping("/sqs/delete")
    public void deleteMessage(@RequestParam String receiptHandle) {
        awsService.deleteMessage(receiptHandle);
    }

    // List all SQS queues
    @GetMapping("/sqs/list")
    public List<String> listQueues() {
        return awsService.listQueues();
    }
}
