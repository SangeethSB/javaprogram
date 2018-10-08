package com.prud.saasservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan(basePackages="com.prud.saasservices")
public class SaasServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaasServicesApplication.class, args);
	}
}
