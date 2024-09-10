package com.noduco.config;

import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClient;

@Configuration
public class AwsSqsConfiguration {
	private String accessKey;
	private String secrateKey;
	
	
	@Bean
	public QueueMessagingTemplate mqt(AmazonSQSAsync asa) {
		return new QueueMessagingTemplate(asa());
	}


	private AmazonSQSAsync asa() {
		return null;
		// TODO Auto-generated method stub
		
	}
  
	
}
