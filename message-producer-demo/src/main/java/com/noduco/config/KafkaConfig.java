package com.noduco.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {
	
	public NewTopic createTopic() {
		return new NewTopic(Topic.NEW_MUSIC, 3, (short) 1);
	}

}
