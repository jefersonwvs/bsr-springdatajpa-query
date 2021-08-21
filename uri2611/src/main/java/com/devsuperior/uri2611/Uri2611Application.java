package com.devsuperior.uri2611;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.uri2611.dtos.MovieDTO;
import com.devsuperior.uri2611.projections.MovieProjection;
import com.devsuperior.uri2611.repositories.MovieRepository;

@SpringBootApplication
public class Uri2611Application implements CommandLineRunner {
	
	@Autowired
	private MovieRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2611Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		List<MovieProjection> list = repository.searchWithSQL("Action");
		List<MovieDTO> result1 = list.stream().map(projection -> new MovieDTO(projection)).collect(Collectors.toList());
		List<MovieDTO> result2 = repository.searchWithJPQL("Action");
		
		System.out.println("\n\nSQL: ");
		result1.forEach(obj -> System.out.println("\t" + obj.getId() + " " + obj.getName()));
		
		System.out.println("\n\nJPQL: ");
		result2.forEach(obj -> System.out.println("\t" + obj.getId() + " " + obj.getName()));
		
	}
}
