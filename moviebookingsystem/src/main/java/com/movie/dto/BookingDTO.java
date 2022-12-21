package com.movie.dto;

import java.util.List;

import com.movie.entity.Ticket;

import lombok.Getter;

import lombok.Setter;

@Getter
@Setter
public class BookingDTO {

	private List<Ticket> ticket;
	private String customerEmail;
	private String customerName;
	
}
