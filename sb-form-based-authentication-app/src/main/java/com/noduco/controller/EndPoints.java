package com.noduco.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EndPoints {
	
	@GetMapping("/message")
	public String messagePrint() {
		return "Helloworld";
	}

}
