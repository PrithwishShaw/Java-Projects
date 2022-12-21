package com.movie.service;

import java.util.List;
import com.movie.entity.Ticket;

public interface AmountService {
	
	float getTicketAmount(List<Ticket> TicketList);
}
