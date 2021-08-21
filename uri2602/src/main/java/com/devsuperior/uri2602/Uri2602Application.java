package com.devsuperior.uri2602;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.uri2602.dtos.CustomerDTO;
import com.devsuperior.uri2602.projections.CustomerProjection;
import com.devsuperior.uri2602.repositories.CustomerRepository;

@SpringBootApplication
public class Uri2602Application implements CommandLineRunner {

	@Autowired
	private CustomerRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2602Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		List<CustomerProjection> list = repository.searchWithSQL("rs");
		List<CustomerDTO> result1 = list
												.stream()
												.map(projection -> new CustomerDTO(projection))
												.collect(Collectors.toList());
		List<CustomerDTO> result2 = repository.searchWithJPQL("rs");
		
		System.out.println("\n*** SQL: ");
		for (CustomerDTO obj : result1) {
			System.out.println("\t" + obj.getName());
		}
		
		System.out.println("\n*** JPQL: ");
		for (CustomerDTO obj : result2) {
			System.out.println("\t" + obj.getName());
		}
		
	}

}