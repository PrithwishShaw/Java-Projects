package com.movie.service;


import java.util.*;

import com.movie.dto.MovieDTO;
import com.movie.entity.Movie;

public interface MovieService {
 	
	String createMovie(Movie movie);
	List<Movie> getAllMovies();
	MovieDTO updateMovie(int id, Movie movie);
	String deleteMovieById(int id);
	List<MovieDTO> getMovieByName(String movieName);
	List<MovieDTO> getMovieByLocation(String movieLocation);
	List<MovieDTO> getMovieByPrice(float moviePrice);
}
