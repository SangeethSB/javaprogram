package com.prud.saasservices.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prud.saasservices.model.Employee;

// STEP 1
@Repository
//Step 2 - change 'class' to 'interface'
//public class EmployeeRepository {

//Step 3 - extends JpaRepository with "Employee" as the first element
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
// Step 4 = Create 	CONTROLLER, refer to TenantsController.java
}
