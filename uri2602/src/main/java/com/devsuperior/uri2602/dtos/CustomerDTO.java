package com.devsuperior.uri2602.dtos;

import com.devsuperior.uri2602.projections.CustomerProjection;

public class CustomerDTO {

	private String name;

	public CustomerDTO() { }

	public CustomerDTO(String name) {
		this.name = name;
	}
	
	public CustomerDTO(CustomerProjection projection) {
		this.name = projection.getName();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}