package com.movie;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.movie.entity.Movie;
import com.movie.repository.MovieRepository;
import com.movie.service.MovieService;

@SpringBootTest
class MovieServiceTest {

	@Autowired
	private MovieService movieService;
	
	@MockBean
	private MovieRepository movieRepository;
	
	@Test
	void addMovie()
	{
		Movie movie= new Movie();
		movie.setMovieName("Avatar");
		movie.setMovieLanguage("English");
		movie.setMovieType("3D");
		movie.setLocation("Inox");
		movie.setMovieTime("3pm");
		movie.setSeatAvailable(87);
		movie.setPrice(350);
		
		Mockito.when(movieRepository.save(movie)).thenReturn(movie);
		assertThat(movieService.createMovie(movie)).isEqualTo
		("Movie is added Successfully");
	}	
	
}
