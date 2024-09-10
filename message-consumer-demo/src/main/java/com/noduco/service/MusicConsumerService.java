package com.noduco.service;

import java.util.Collections;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
//import org.apache.kafka.common.message.ConsumerProtocolAssignment.TopicPartition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;
import org.apache.kafka.common.TopicPartition;

import com.noduco.config.Topic;

@Service
public class MusicConsumerService {
	
	 @Autowired
	 private KafkaConsumer<String, String> kafkaConsumer;
	 
	 
	@KafkaListener(topics = Topic.NEW_MUSIC, groupId = "group_2")
	 public void consume(ConsumerRecord<String, String> record, Acknowledgment acknowledgment) {
        String message = record.value();
        System.out.println("Consumed message: " + message);

        // Get current offset
        long offset = record.offset();
        System.out.println("Current offset: " + offset);
        
        // Get committed offset
        TopicPartition topicPartition = new TopicPartition(record.topic(), record.partition());
        OffsetAndMetadata committed = kafkaConsumer.committed(Collections.singleton(topicPartition)).get(topicPartition);
        System.out.println("Committed offset: " + (committed != null ? committed.offset() : "No committed offset"));
        if(committed.offset() == offset) {
        	System.out.println("This message alreday commited");
        }
        // Manually commit the offset
        acknowledgment.acknowledge();
    }
}
