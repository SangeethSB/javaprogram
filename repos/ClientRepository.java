package com.prud.saasservices.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prud.saasservices.model.Client;


@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

}

