package com.devsuperior.uri2611.dtos;

import com.devsuperior.uri2611.projections.MovieProjection;

public class MovieDTO {

	private Long id;
	private String name;

	public MovieDTO() {
	}
	
	public MovieDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public MovieDTO(MovieProjection projection) {
		this.id = projection.getId();
		this.name = projection.getName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}