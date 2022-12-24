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
	
	@PostMapping("/addMovie/{role}")
	public ResponseEntity<String> createMovie(@PathVariable("role") String role,@RequestBody MovieDTO movieDTO)
	{
		final Movie movie= movieConverter.convertToMovieEntity(movieDTO);
		if(role.equals("admin"))
		{
			movieService.createMovie(movie);
			return new ResponseEntity<String>("Movie is added Successfully", 
					HttpStatus.CREATED);
		}
		else
		{
			return new ResponseEntity<String>("You are not an admin", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/getAllMovies")
	public List<Movie> getAllMovies()
	{
		return movieService.getAllMovies();
	}
	
	@PutMapping("/updateMovie/{role}/{id}")
	public ResponseEntity<?> updateMovie(@PathVariable("role") String role, @PathVariable("id") int id, @RequestBody MovieDTO movieDTO)
	{
		if(role.equals("admin"))
		{
			Movie movie1= movieConverter.convertToMovieEntity(movieDTO);
			return new ResponseEntity<MovieDTO>(movieService.updateMovie(id, movie1), HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("You are not an admin", HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/deleteMovieById/{role}/{id}")
	public ResponseEntity<String> deleteMovieById(@PathVariable("role") String role, @PathVariable ("id") int id)
	{
		if(role.equals("admin"))
		{
			return new ResponseEntity<String>(movieService.deleteMovieById(id), HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("You are not an admin", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/getMovieByGenre/{genre}")
	public List<MovieDTO> getMovieByGenre(@PathVariable("genre") String genre)
	{
		return movieService.getMovieByGenre(genre);
	}
	
	@GetMapping("/getMovieByPrice/{price}")
	public List<MovieDTO> getMovieByPrice(@PathVariable("price") float moviePrice)
	{
		return movieService.getMovieByPrice(moviePrice);
	}
	
	@GetMapping("/getMovieByPriceBetween/{startPrice}/{endPrice}")
	public List<MovieDTO> getMovieByPriceBetween(@PathVariable("startPrice") float startPrice,
			@PathVariable("endPrice") float endPrice)
	{
		return movieService.getMovieBYPriceBetween(startPrice, endPrice);
	}	
}
