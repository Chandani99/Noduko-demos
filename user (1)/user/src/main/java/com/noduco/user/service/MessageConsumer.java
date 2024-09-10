package com.noduco.user.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer {
	@KafkaListener(topics = "message-group1", groupId = "group-2")
    public void consumeMessage(String message) {
        System.out.println("Consumed Message: "+message);
    }
}
