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
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="movie_details")
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int movieId;
	
	@Column(name="movie_name", nullable = false, length = 20)
	private String movieName;
	
	@Column(name="language", nullable = false, length = 10)
	private String movieLanguage;
	
	@Column(name="genre", nullable = false, length = 10)
	private String movieGenre;
	
	@Column(name="type", nullable = false, length = 10)
	private String movieType;
	
	@Column(name="location", nullable = false, length = 10)
	private String location;
	
	@Column(name="timing", nullable = false,length = 10)
	private String movieTime;
	
	@Column(name="seat_available", nullable = false, length = 3)
	private int seatAvailable;
	
	@Column(name="movie_price", nullable = false, length = 3)
	private float price;	
}
