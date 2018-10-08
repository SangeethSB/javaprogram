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
import com.prud.saasservices.model.Tenant;
import com.prud.saasservices.repos.TenantsRepository;

// After you create data model and crudrepository, create controller which
// exposes RESTful (Representational State Transfer) APIs.
// These APIs are accessed through URL and returns JSON objects.
// Required conversion from table record to JSON format is automatically 
// taken care by Spring Framework.

@RestController
// This is the base URL (after hostname & port number) to access data from
// different methods in this controller.
// i.e., http://localhost:8080/api/tenants/v1 is the base URL
// Note that the http://localhost:8080 might change in production environment
// where the app will be hosted in a cloud server
@RequestMapping("/api/tenants/v1")
public class TenantsController {

	// Autowired means, we need not create an instance of the TenantsRepository
	// using new operator
	// Spring framework creates the instance tenantsRepos automatically
	// This is called dependency injection.
	@Autowired
	TenantsRepository tenantsRepos;

	// Get All the records from tenants table
	// This is accessed from URL as http://localhost:8080/api/tenants/v1/tenants
	// @Get mapping means, this URL should be accessed with GET method from
	// html/browser
	@GetMapping("/tenants")
	public List<Tenant> getAllTenants() {
		return tenantsRepos.findAll();
	}

	// Creates a new tenant record in the database
	// Form should submit the column name, value pair in JSON format. This is
	// received
	// through @Valid @RequestBody annotations in the parameter to this method.
	// @Postmapping means this URL should be accessed with POST method from
	// html/browser
	@PostMapping("/tenants")
	public Tenant createTenant(@Valid @RequestBody Tenant tenant) {
	
		return tenantsRepos.save(tenant);
	}

	// Get a Single Tenant record
	// Parameter is the tenant ID in the URL
	// example: http://localhost:8080/api/tenants/v1/tenants/123
	// This method returns the record of tenant where the ID is equals to 123
	@GetMapping("/tenants/{id}")
	public Tenant getTenantById(@PathVariable(value = "id") Long tenantId) {
		
		return tenantsRepos.findById(tenantId)
				.orElseThrow(() -> new ResourceNotFoundException("Tenant", "id", tenantId));
	}

	// Update a Tenant
	@PutMapping("/tenants/{id}")
	public Tenant updateTenant(@PathVariable(value = "id") Long tenantId, @Valid @RequestBody Tenant tenantDetails) {

		Tenant tenant = tenantsRepos.findById(tenantId)
				.orElseThrow(() -> new ResourceNotFoundException("Tenant", "id", tenantId));

		Tenant updatedTenant = tenantsRepos.save(tenantDetails);
		return updatedTenant;
	}

	// Delete a Tenant
	@DeleteMapping("/tenants/{id}")
	public ResponseEntity<?> deleteTenant(@PathVariable(value = "id") Long tenantId) {
		
		Tenant tenant = tenantsRepos.findById(tenantId)
				.orElseThrow(() -> new ResourceNotFoundException("Tenant", "id", tenantId));

		tenantsRepos.delete(tenant);
		return ResponseEntity.ok().build();
	}
}
