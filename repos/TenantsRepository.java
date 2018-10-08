package com.prud.saasservices.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prud.saasservices.model.Tenant;

// JpaRepository - provides useful methods to work with database tables
//C - CREATE records
//R - READ records
//U - UPDATE records
//D - DELETE records

// We first have to create the Data Model under com.prud.saasservices.model
// Use the model name <<model>>Repository extends JpaRepository<<<modelname>>, Long>
// The following is based on model "Tenant". Same way create for new models.

@Repository
public interface TenantsRepository extends JpaRepository<Tenant, Long> {

}
