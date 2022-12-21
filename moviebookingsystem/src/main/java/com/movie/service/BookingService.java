package com.movie.service;

import com.movie.dto.BookingDTO;
import com.movie.dto.TicketDTO;
import com.movie.entity.Booking;

public interface BookingService {

	Booking getBookingDetailById(int bookingId);
	TicketDTO bookTicket(BookingDTO bookingDTO);
	String cancelTicket(int Id);
}
