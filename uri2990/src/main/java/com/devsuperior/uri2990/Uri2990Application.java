package com.devsuperior.uri2990;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.uri2990.dto.EmpregadoDeptDTO;
import com.devsuperior.uri2990.projections.EmpregadoDeptProjection;
import com.devsuperior.uri2990.repositories.EmpregadoRepository;

@SpringBootApplication
public class Uri2990Application implements CommandLineRunner {

	@Autowired
	private EmpregadoRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2990Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<EmpregadoDeptProjection> list = repository.searchWithSQL();
		List<EmpregadoDeptDTO> result1 = list.stream().map(projection -> new EmpregadoDeptDTO(projection)).collect(Collectors.toList());
		System.out.println("\n\n***SQL:");
		result1.forEach(obj -> System.out.println("\t" + obj));
		
		List<EmpregadoDeptDTO> result2 = repository.searchWithJPQL();
		System.out.println("\n\n***JPQL:");
		result2.forEach(obj -> System.out.println("\t" + obj));
	}
}
