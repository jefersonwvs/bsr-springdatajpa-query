package com.devsuperior.uri2611.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.uri2611.dtos.MovieDTO;
import com.devsuperior.uri2611.entities.Movie;
import com.devsuperior.uri2611.projections.MovieProjection;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

	@Query(nativeQuery = true,
			 value = "SELECT movies.id, name " +
					   "FROM movies " + 
					   "INNER JOIN genres ON id_genres = genres.id " +
					   "WHERE genres.description = :genre")
	List<MovieProjection> searchWithSQL(String genre);
	
	@Query(value = "SELECT new com.devsuperior.uri2611.dtos.MovieDTO(obj.id, obj.name) " + 
						"FROM Movie obj " +
						"WHERE obj.genre.description = :genre")
	List<MovieDTO> searchWithJPQL(String genre);
	
}