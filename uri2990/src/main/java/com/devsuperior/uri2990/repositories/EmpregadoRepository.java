package com.devsuperior.uri2990.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.uri2990.dto.EmpregadoDeptDTO;
import com.devsuperior.uri2990.entities.Empregado;
import com.devsuperior.uri2990.projections.EmpregadoDeptProjection;

public interface EmpregadoRepository extends JpaRepository<Empregado, Long> {
	
	@Query(nativeQuery = true,
			value = "SELECT cpf, enome, dnome "
					+ "FROM empregados "
					+ "INNER JOIN departamentos ON empregados.dnumero = departamentos.dnumero "
					+ "LEFT JOIN trabalha ON cpf_emp = cpf "
					+ "WHERE cpf_emp IS null "
					+ "ORDER BY cpf")
	List<EmpregadoDeptProjection> searchWithSQL();
	
	@Query(value = "SELECT new com.devsuperior.uri2990.dto.EmpregadoDeptDTO(obj.cpf, obj.enome, obj.departamento.dnome) "
					 + "FROM Empregado obj "
					 + "WHERE obj.projetosOndeTrabalha.size = 0 "
					 + "ORDER BY obj.cpf")
	List<EmpregadoDeptDTO> searchWithJPQL();
	

}
