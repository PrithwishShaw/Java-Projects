package com.movie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.movie.entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer>{

	@Query("from Movie where lower(movieName) like :m%")
	List<Movie> getMovieByName(@Param("m") String movieName);
	
	@Query("from Movie where lower(location) like :m%")
	List<Movie> getMovieByLocation(@Param("m") String location);
	
	@Query("from Movie where lower(genre) like :m%")
	List<Movie> getMovieByGenre(@Param("m") String genre);
	
	List<Movie> getMovieByPrice( float price);
	
	List<Movie> getMovieByPriceBetween(float startPrice, float endPrice);
	
}
