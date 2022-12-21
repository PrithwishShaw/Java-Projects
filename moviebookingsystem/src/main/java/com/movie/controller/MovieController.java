package com.movie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.movie.dto.MovieDTO;
import com.movie.entity.Movie;
import com.movie.service.MovieService;
import com.movie.util.MovieConverter;





@RestController
public class MovieController {

	@Autowired
	private MovieService movieService;
	
	@Autowired
	private MovieConverter movieConverter;
	
	@PostMapping("/addMovie")
	public ResponseEntity<String> createMovie(@RequestBody MovieDTO movieDTO)
	{
		final Movie movie= movieConverter.convertToMovieEntity(movieDTO);
		movieService.createMovie(movie);
		return new ResponseEntity<String>("Movie is added Successfully", 
				HttpStatus.CREATED);
	}
	
	@GetMapping("/getAllMovies")
	public List<Movie> getAllMovies()
	{
		return movieService.getAllMovies();
	}
	
	@PutMapping("/updateMovie/{id}")
	public MovieDTO updateMovie(@PathVariable("id") int id, @RequestBody MovieDTO movieDTO)
	{
		Movie movie1= movieConverter.convertToMovieEntity(movieDTO);
		return movieService.updateMovie(id, movie1);
	}
	
	@DeleteMapping("/deleteMovieById/{id}")
	public String deleteMovieById(@PathVariable ("id") int id)
	{
		return movieService.deleteMovieById(id);
	}
	
	@GetMapping("/getMovieByName/{name}")
	public List<MovieDTO> getMovieByName(@PathVariable("name") String movieName)
	{
		return movieService.getMovieByName(movieName);
	}
	
	@GetMapping("/getMovieByLocation/{name}")
	public List<MovieDTO> getMovieByLocation(@PathVariable("name") String movieLocation)
	{
		return movieService.getMovieByLocation(movieLocation);
	}	
}
