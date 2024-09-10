package com.noduco.admin.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.noduco.admin.service.MessageProducerService;

import jakarta.annotation.Generated;

@RestController
@RequestMapping("/message-producer")
public class MessageProducerController {
	
	@Autowired
	private MessageProducerService mps;
	
	@GetMapping("/")
	public ResponseEntity<?> produceMessageHandler(@RequestParam("key") String key, @RequestParam("message") String messaage){
		mps.publishMessage(key, messaage);
		return new ResponseEntity<>(Map.of("message: ", "message produced"), HttpStatus.OK);
	}

}
