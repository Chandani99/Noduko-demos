package com.noduco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.noduco.service.SqsService;

@RestController
@RequestMapping("/sqs")
public class SqsController {
	
	@Autowired
    private SqsService awsService;

    // Create an SQS queue
    @PostMapping("/queue/create")
    public String createQueue(@RequestParam String queueName) {
        return awsService.createQueue(queueName);
    }
    
    // Create an SQS queue
    @PostMapping("/queue/create-fifo")
    public String createFIFOQueue(@RequestParam String queueName) {
    	
        return awsService.createQueue(queueName);
    }
    
    // Send a message to SQS queue
    @PostMapping("/message/send")
    public String sendMessage(@RequestParam String queueUrl, @RequestParam String message) {
        return awsService.sendMessage(queueUrl, message);
    }
    
    

}
