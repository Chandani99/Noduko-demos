package com.noduco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.noduco.service.SnsService;

@RestController
@RequestMapping("/sns")
public class SnsCotroller {
	 @Autowired
	    private SnsService snsService;

	    @PostMapping("/publish")
	    public String publishMessage(@RequestParam String message, @RequestParam String topicArn) {
	        return snsService.publishMessage(message, topicArn);
	    }

}
