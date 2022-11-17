package com.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="player_address")
public class PlayerAddress {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    
    //variables
    @Column(name="id")
    private int id;
    
    @Column(name="country")
    private String country;
    
    @Column(name="state")
    private String state;
    
    @ManyToMany(mappedBy="playerAddress")
    private List<Player> player;

	public PlayerAddress() {
		super();
	}

	public PlayerAddress(String country, String state) {
		super();
		this.country = country;
		this.state = state;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<Player> getPlayer() {
		return player;
	}

	public void setPlayer(List<Player> player) {
		this.player = player;
	}

	@Override
	public String toString() {
		return "PlayerAddress [id=" + id + ", country=" + country + ", state=" + state + "]";
	}
}
