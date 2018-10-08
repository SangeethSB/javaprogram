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
import com.prud.saasservices.model.Client;
import com.prud.saasservices.repos.ClientRepository;

@RestController
@RequestMapping("/api/client/v1")
public class ClientController {
	@Autowired
	ClientRepository cliRepos;
	
	@GetMapping("/clients")
	public List<Client> getAllClients(){
		return cliRepos.findAll();
	}
	
	@PostMapping("/clients")
	public Client createClient(@Valid @RequestBody Client Client) {
	
		return cliRepos.save(Client);
	}

			@GetMapping("/clients/{id}")
	public Client getClientById(@PathVariable(value = "id") Long clientId) {
		
		return cliRepos.findById(clientId)
				.orElseThrow(() -> new ResourceNotFoundException("Client", "id", clientId));
	}

	
	@PutMapping("/clients/{id}")
	public Client updateClient(@PathVariable(value = "id") Long clientId, @Valid @RequestBody Client clientDetails) {

		Client Client = cliRepos.findById(clientId)
				.orElseThrow(() -> new ResourceNotFoundException("Client", "id", clientId));

		Client updatedClient = cliRepos.save(clientDetails);
		return updatedClient;
	}

	
	@DeleteMapping("/clients/{id}")
	public ResponseEntity<?> deleteClient(@PathVariable(value = "id") Long clientId) {
		
		Client Client = cliRepos.findById(clientId)
				.orElseThrow(() -> new ResourceNotFoundException("Client", "id", clientId));

		cliRepos.delete(Client);
		return ResponseEntity.ok().build();
	}

}
