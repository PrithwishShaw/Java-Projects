package com.entity;


import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

	@Entity
	@Table(name="Player")
	public class Player {

	//variables
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    @Column(name="first_name")
    private String firstName;
    
    @Column(name="last_name")
    private String lastName;

    @Column(name="category")
    private String category;
    
    @ManyToMany
    private List<PlayerAddress> playerAddress;

	public Player() {
		super();
	}

	public Player(String firstName, String lastName, String category) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<PlayerAddress> getPlayerDetail() {
		return playerAddress;
	}

	public void setPlayerAddress(List<PlayerAddress> playerAddress) {
		this.playerAddress = playerAddress;
	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", category=" + category
				+ "]";
	}
}
