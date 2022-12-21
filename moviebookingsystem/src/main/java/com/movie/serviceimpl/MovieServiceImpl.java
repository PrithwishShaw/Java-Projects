package com.movie.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.dto.MovieDTO;
import com.movie.entity.Movie;
import com.movie.exception.ResourceNotFoundException;
import com.movie.repository.MovieRepository;
import com.movie.service.MovieService;
import com.movie.util.MovieConverter;

@Service
public class MovieServiceImpl implements MovieService{

	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private MovieConverter movieConverter;
	
	@Override
	public String createMovie(Movie movie) {
		String message=null;
		movieRepository.save(movie);
		if(movie!=null)
		{
			message="Movie is added Successfully";
		}
		return message;
	}

	@Override
	public List<Movie> getAllMovies() {
		
		return movieRepository.findAll();
	}

	@Override
	public MovieDTO updateMovie(int id, Movie movie) {
		Movie existingMovie = movieRepository.findById(id).orElseThrow(()->
		new ResourceNotFoundException("Movie", "ID", id));
		existingMovie.setMovieName(movie.getMovieName());
		existingMovie.setLocation(movie.getLocation());
		existingMovie.setMovieLanguage(movie.getMovieLanguage());
		existingMovie.setMovieTime(movie.getMovieTime());
		existingMovie.setMovieType(movie.getMovieType());
		existingMovie.setPrice(movie.getPrice());
		existingMovie.setSeatAvailable(movie.getSeatAvailable());
		
		movieRepository.save(existingMovie);
		
		return movieConverter.convertToMovieDTO(existingMovie);
	}

	@Override
	public String deleteMovieById(int id) {
		String message=null;
		Optional<Movie> movie= Optional.ofNullable(movieRepository.findById(id).orElseThrow(()->
		new ResourceNotFoundException("Movie", "ID", id)));
		if(movie.isPresent())
		{
			movieRepository.deleteById(id);
			message= "Movie have been deleted";
		}
		else 
		{
			message= "Movie details not found";
		}
		return message;
	}

	@Override
	public List<MovieDTO> getMovieByName(String movieName) {
		List<Movie> movies= movieRepository.getMovieByName(movieName);
		List<MovieDTO> movieDTO= new ArrayList<>();
		for(Movie m: movies)
		{
			movieDTO.add(movieConverter.convertToMovieDTO(m));
		}
		return movieDTO;
	}

	@Override
	public List<MovieDTO> getMovieByLocation(String movieLocation) {
		List<Movie> movies= movieRepository.getMovieByLocation(movieLocation);
		List<MovieDTO> movieDTO= new ArrayList<>();
		for(Movie m: movies)
		{
			movieDTO.add(movieConverter.convertToMovieDTO(m));
		}
		return movieDTO;
	}

	@Override
	public List<MovieDTO> getMovieByPrice(float moviePrice) {
		List<Movie> movies= movieRepository.getMovieByPrice(moviePrice);
		List<MovieDTO> movieDTO= new ArrayList<>();
		for(Movie m: movies)
		{
			movieDTO.add(movieConverter.convertToMovieDTO(m));
		}
		return movieDTO;
	}
	
}
