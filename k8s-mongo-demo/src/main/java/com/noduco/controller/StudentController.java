package com.noduco.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.noduco.document.Student;
import com.noduco.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentService sService;
	
	@PutMapping("/")
	public ResponseEntity<?> saveStudentHandler(@RequestBody Student student){
		String result = sService.saveStudent(student);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
    @GetMapping("/")
	public ResponseEntity<?> getAllStudentHandler(){
		List<Student> result = sService.getAllStudent();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateStudentDetailesHandler(@PathVariable Object id, @RequestParam String course){
		String result = sService.updateStudentDetails(id, course);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
