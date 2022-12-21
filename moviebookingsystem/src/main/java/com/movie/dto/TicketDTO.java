package com.movie.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketDTO {
	
	private float amount;
	private int invoiceNumber;
	private String date;
	private int bookingId;
	private String notes;
	
}
