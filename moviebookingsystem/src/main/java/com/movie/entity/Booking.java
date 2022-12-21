package com.movie.entity;

import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="booking")
public class Booking {
	
	  	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;
	  	
	  	@OneToOne
	  	private Customer customer;
	  	
	  	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Ticket.class)
	  	@JoinColumn(name = "booking_id", referencedColumnName = "id")
	  	private List<Ticket> booking;

		public Booking(Customer customer, List<Ticket> booking) {
			super();
			this.customer = customer;
			this.booking = booking;
		}			
}
