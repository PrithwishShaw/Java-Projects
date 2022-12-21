package com.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.movie.dto.BookingDTO;
import com.movie.dto.TicketDTO;
import com.movie.entity.Booking;
import com.movie.service.BookingService;

@RestController
@RequestMapping("/api")
public class BookingTicketController {

	@Autowired
	private BookingService bookingService;
	
	@GetMapping("/getBookingDetailsById/{bookingId}") 
	public Booking getBookingDetails(@PathVariable int bookingId)
	{
		Booking booking= bookingService.getBookingDetailById(bookingId);
		return booking;
	}
	
	@PostMapping("/bookTicket")
	public TicketDTO bookTicket(@RequestBody BookingDTO bookingDTO)
	{
		return bookingService.bookTicket(bookingDTO);
	}
	
	@DeleteMapping("/cancelTicket/{id}")
	public String cancelTicket(@PathVariable("id") int id)
	{
		return bookingService.cancelTicket(id);
	}
}
