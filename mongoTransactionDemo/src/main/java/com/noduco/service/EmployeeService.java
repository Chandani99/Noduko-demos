package com.noduco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.noduco.document.Employee;
import com.noduco.repository.EmployeeRepo;

public class EmployeeService {
	  @Autowired
      private EmployeeRepo eRepo;

      @Transactional
      public void performTransactionalOperation(Employee entity1, Employee entity2) {
    	  try {
    		  eRepo.save(entity1);
    	      eRepo.save(entity2);
    	  }catch(Exception e) {
    		  e.getMessage();
    	  }
        
        // You can add more operations here
    }

}
