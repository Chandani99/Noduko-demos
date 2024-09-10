package com.noduco.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
	@Id
    private String id;
    private String name;
    private String email;
    private String contactNo;
}
