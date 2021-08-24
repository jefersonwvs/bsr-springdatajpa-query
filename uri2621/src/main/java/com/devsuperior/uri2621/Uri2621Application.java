package com.devsuperior.uri2621;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.uri2621.dtos.ProductDTO;
import com.devsuperior.uri2621.projections.ProductProjection;
import com.devsuperior.uri2621.repositories.ProductRepository;

@SpringBootApplication
public class Uri2621Application implements CommandLineRunner {

	@Autowired
	private ProductRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(Uri2621Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		List<ProductProjection> list = repository.searchWithSQL(10, 20, "P");
		List<ProductDTO> result1 = list.stream().map(projection -> new ProductDTO(projection))
				.collect(Collectors.toList());
		List<ProductDTO> result2 = repository.searchWithJPQL(10, 20, "P");

		System.out.println("\n\n***SQL:");
		result1.forEach(obj -> System.out.println("\t" + obj));

		System.out.println("\n\n***JPQL:");
		result2.forEach(obj -> System.out.println("\t" + obj));

	}

}