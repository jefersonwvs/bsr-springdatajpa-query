package com.devsuperior.uri2621.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.uri2621.dtos.ProductDTO;
import com.devsuperior.uri2621.entities.Product;
import com.devsuperior.uri2621.projections.ProductProjection;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query(nativeQuery = true,
			value = "SELECT products.name "
					+ "FROM products "
					+ "INNER JOIN providers "
					+ "ON products.id_providers = providers.id "
					+ "WHERE products.amount BETWEEN :min AND :max AND providers.name LIKE CONCAT(:beginName, '%')")
	List<ProductProjection> searchWithSQL(Integer min, Integer max, String beginName);
	
	@Query(value = "SELECT new com.devsuperior.uri2621.dtos.ProductDTO(obj.name) "
					 + "FROM Product obj "
					 + "WHERE obj.amount BETWEEN :min AND :max AND obj.provider.name LIKE CONCAT(:beginName, '%')")
	List<ProductDTO> searchWithJPQL(Integer min, Integer max, String beginName);
	
}