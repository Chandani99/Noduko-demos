package com.noduco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.noduco.service.MusicProducerService;

@RestController
@RequestMapping("/music-producer")
public class MusicProducerController {
	  @Autowired
	  private MusicProducerService mps;

	    @GetMapping("/publish")
	    public String publishMessage(@RequestParam("message") String message) {
	        mps.sendMessage(message);
	        return "Message published successfully!";
	    }
}
