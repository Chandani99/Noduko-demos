package com.noduco.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

class User{
	Integer id;
	String name;
	Integer salary;
	
	User(Integer id, String name, Integer salary){
		this.id = id;
		this.name = name;
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", salary=" + salary + "]";
	}
	
}

@RestController
@RequestMapping("/user")
public class UserController {
      
      @GetMapping("/")
      public String getMethodName() {
    	  User user = new User(1, "Chandani", 150000);
          return new String("User Object: "+user);
      }
      
      
      
      
}
