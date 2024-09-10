package com.noduco.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.noduco.document.Employee;
import com.noduco.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	 @Autowired
	 private EmployeeService eService;

	 @PostMapping("/transaction")
	 public ResponseEntity<String> performTransaction(@RequestBody List<Employee> entities) {
	     if (entities.size() == 2) {
	         eService.performTransactionalOperation(entities.get(0), entities.get(1));
	         return ResponseEntity.ok("Transaction successful");
	     }
	     return ResponseEntity.badRequest().body("Invalid input data");
	 }

}
