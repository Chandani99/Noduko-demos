package com.noduco.reposatory;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.noduco.document.Student;

@Repository
public interface StudentRepo extends MongoRepository<Student, Object>{

}
