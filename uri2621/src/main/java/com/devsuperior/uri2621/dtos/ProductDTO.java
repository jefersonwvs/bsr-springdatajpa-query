package com.devsuperior.uri2621.dtos;

import com.devsuperior.uri2621.projections.ProductProjection;

public class ProductDTO {

	private String name;

	public ProductDTO() {
	}

	public ProductDTO(String name) {
		this.name = name;
	}

	public ProductDTO(ProductProjection projection) {
		this.name = projection.getName();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

}