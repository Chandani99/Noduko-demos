package com.noduco.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noduco.document.Student;
import com.noduco.reposatory.StudentRepo;

@Service
public class StudentService {
	@Autowired
	private StudentRepo sRepo;
	
	public String saveStudent(Student student) {
		sRepo.save(student);
		return "Student data saved";
	}

	public List<Student> getAllStudent(){
		return sRepo.findAll();
	}
	
	public String updateStudentDetails(Object id,String course) {
		Student s = sRepo.findById(id).get();
		s.setCourse(course);
		sRepo.save(s);
		return "Student detail is updated successfully";
	}
	
}
