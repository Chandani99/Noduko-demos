package com.noduco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.noduco.entity.CustomUser;
import com.noduco.service.CustomUserDetailsService;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private CustomUserDetailsService cuService;
	
	@PostMapping("/register")
	public ResponseEntity<String> registerUserHandler(@RequestBody CustomUser user) {
		cuService.addUser(user);
		return new ResponseEntity<> ("user regstered successfully", HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "Welcome to the Admin Dashboard!";
    }
	
	@GetMapping("/user/profile")
    public String userProfile() {
        return "Welcome to the User Profile!";
    }

}
