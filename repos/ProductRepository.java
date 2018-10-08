package com.prud.saasservices.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prud.saasservices.model.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}

