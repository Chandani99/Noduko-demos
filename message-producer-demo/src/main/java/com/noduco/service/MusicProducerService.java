package com.noduco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.noduco.config.Topic;

@Service
public class MusicProducerService {
	
	 @Autowired
	    private KafkaTemplate<String, String> kafkaTemplate;

	    public void sendMessage(String message) {
	        kafkaTemplate.send(Topic.NEW_MUSIC, message);
	    }
}
