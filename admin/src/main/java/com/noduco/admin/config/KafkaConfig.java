package com.noduco.admin.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {
	@Bean
	public NewTopic createnewTopic() {
		return new NewTopic(Topics.GROUP_MESSAGE, 5, (short) 1);
	}

}
