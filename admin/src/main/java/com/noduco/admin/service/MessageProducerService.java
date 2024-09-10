package com.noduco.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.noduco.admin.config.Topics;

@Service
public class MessageProducerService {
	@Autowired
	private KafkaTemplate<String, Object> ktemplate;
	
	public void publishMessage(String name, String messgae) {
		ktemplate.send(Topics.GROUP_MESSAGE, 0, name, messgae);
	}

}
