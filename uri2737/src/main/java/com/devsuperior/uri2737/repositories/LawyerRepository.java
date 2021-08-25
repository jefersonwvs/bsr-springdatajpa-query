package com.devsuperior.uri2737.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.uri2737.entities.Lawyer;
import com.devsuperior.uri2737.projections.LawyerMinProjection;

public interface LawyerRepository extends JpaRepository<Lawyer, Long> {

	@Query(nativeQuery = true,
			 value = "(SELECT name, customers_number AS customersNumber"
					 + " FROM lawyers"
					 + " ORDER BY customers_number DESC"
					 + " LIMIT 1)"
					 + " UNION ALL "
					 + "(SELECT name, customers_number"
					 + " FROM lawyers"
					 + " ORDER BY customers_number ASC"
					 + " LIMIT 1)"
					 + " UNION ALL "
					 + "(SELECT 'Average', ROUND(AVG(customers_number), 0)\n"
					 + " FROM lawyers)")
	List<LawyerMinProjection> searchWithSQL();
	
}
