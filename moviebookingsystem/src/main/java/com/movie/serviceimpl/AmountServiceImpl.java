package com.movie.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.entity.Movie;
import com.movie.entity.Ticket;
import com.movie.repository.MovieRepository;
import com.movie.service.AmountService;

@Service
public class AmountServiceImpl implements AmountService{
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Override
	public float getTicketAmount(List<Ticket> TicketList) {
		float totalTicketAmount = 0f;
        float singleTicketAmount = 0f;
        int seatsAvailable = 0;
	
        for(Ticket t : TicketList)
        {
        	int movieId= t.getMovieId();
        	Optional<Movie> movie= movieRepository.findById(movieId);
        	if(movie.isPresent())
        	{
        		Movie movie1= movie.get();
        		if(movie1.getSeatAvailable() < t.getSeats())
        		{
        			singleTicketAmount= movie1.getPrice() * movie1.getSeatAvailable();
        			t.setSeats(movie1.getSeatAvailable());
        		}
        		else
        		{
        			singleTicketAmount= t.getSeats() * movie1.getPrice();
        			seatsAvailable=movie1.getSeatAvailable() - t.getSeats();
        		}
        		totalTicketAmount= totalTicketAmount + singleTicketAmount;
        		movie1.setSeatAvailable(seatsAvailable);
        		seatsAvailable=0;
        		t.setMovieName(movie1.getMovieName());
        		t.setTotalAmount(singleTicketAmount);
        		movieRepository.save(movie1);
        	}
        }
        return totalTicketAmount;
	}
}
