package com.prud.saasservices.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prud.saasservices.exception.ResourceNotFoundException;
import com.prud.saasservices.model.Employee;
import com.prud.saasservices.repos.EmployeeRepository;

@RestController
@RequestMapping("/api/employees/v1")
public class EmployeesController {

	@Autowired
	EmployeeRepository empRepos;
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return empRepos.findAll();
	}
	
	@PostMapping("/employees")
	public Employee createEmployee(@Valid @RequestBody Employee Employee) {
	
		return empRepos.save(Employee);
	}

	// Get a Single Employee record
	// Parameter is the Employee ID in the URL
	// example: http://localhost:8080/api/employees/v1/employees/123
	// This method returns the record of Employee where the ID is equals to 123
	@GetMapping("/employees/{id}")
	public Employee getEmployeeById(@PathVariable(value = "id") Long employeeId) {
		
		return empRepos.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));
	}

	// Update a Employee
	@PutMapping("/employees/{id}")
	public Employee updateEmployee(@PathVariable(value = "id") Long employeeId, @Valid @RequestBody Employee employeeDetails) {

		Employee Employee = empRepos.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));

		Employee updatedEmployee = empRepos.save(employeeDetails);
		return updatedEmployee;
	}

	// Delete a Employee
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable(value = "id") Long employeeId) {
		
		Employee Employee = empRepos.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));

		empRepos.delete(Employee);
		return ResponseEntity.ok().build();
	}
}
