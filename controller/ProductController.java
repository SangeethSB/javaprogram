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
import com.prud.saasservices.model.Product;
import com.prud.saasservices.repos.ProductRepository;

@RestController
@RequestMapping("/api/product/v1")
public class ProductController {
	@Autowired
	ProductRepository prdRepos;
	
	@GetMapping("/products")
	public List<Product> getAllProducts(){
		return prdRepos.findAll();
	}
	
	@PostMapping("/products")
	public Product createProduct(@Valid @RequestBody Product Product) {
	
		return prdRepos.save(Product);
	}

			@GetMapping("/products/{id}")
	public Product getProductById(@PathVariable(value = "id") Long productId) {
		
		return prdRepos.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
	}

	
	@PutMapping("/products/{id}")
	public Product updateProduct(@PathVariable(value = "id") Long productId, @Valid @RequestBody Product productDetails) {

		Product Product = prdRepos.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));

		Product updatedProduct = prdRepos.save(productDetails);
		return updatedProduct;
	}

	
	@DeleteMapping("/products/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable(value = "id") Long productId) {
		
		Product Product = prdRepos.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));

		prdRepos.delete(Product);
		return ResponseEntity.ok().build();
	}

}
