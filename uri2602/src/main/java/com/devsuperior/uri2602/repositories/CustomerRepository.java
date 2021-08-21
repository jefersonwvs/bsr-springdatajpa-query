package com.devsuperior.uri2602.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.uri2602.dtos.CustomerDTO;
import com.devsuperior.uri2602.entities.Customer;
import com.devsuperior.uri2602.projections.CustomerProjection;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	@Query(nativeQuery = true,
			 value = "SELECT name FROM customers WHERE UPPER(state) = UPPER(:state)")
	List<CustomerProjection> searchWithSQL(String state);
	
	@Query(value = "SELECT new com.devsuperior.uri2602.dtos.CustomerDTO(obj.name) " +
						"FROM Customer obj " +
						"WHERE UPPER(obj.state) = UPPER(:state)")
	List<CustomerDTO> searchWithJPQL(String state);
	
}