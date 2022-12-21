package com.movie.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="booked_ticket")
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ticketId;
	
	@Column(name="movie_id")
	private int movieId;
	
	@Column(name="movie_name")
	private String movieName;
	
	@Column(name="booked_seats")
	private int seats;
	
	@Column(name="total_amaount")
	private float totalAmount;


	public Ticket(int movieId, int seats) {
		super();
		this.movieId = movieId;
		this.seats = seats;
	}

	public Ticket(int movieId, String movieName, int seats, float totalAmount) {
		super();
		this.movieId = movieId;
		this.movieName = movieName;
		this.seats = seats;
		this.totalAmount = totalAmount;
	}
}
