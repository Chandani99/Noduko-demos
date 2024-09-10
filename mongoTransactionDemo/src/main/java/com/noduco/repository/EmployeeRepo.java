package com.noduco.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.noduco.document.Employee;

@Repository
public interface EmployeeRepo extends MongoRepository<Employee, String>{

}
