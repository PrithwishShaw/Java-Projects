/*
Creating the POJO class
 */

package com.player.sportsmanagement;

import jakarta.persistence.*;

@Entity
@Table(name="player")  //table name

//POJO class
public class Player {

	@Id
	@Column(name="playerId")	//column name
	private int id;
	
	@Column(name="playerFirstName")	//column name
	private String playerFirstName;
	
	@Column(name="playerLastName")	//column name
	private String playerLastName;
	
	@Column(name="playerAge")	//column name
	private int playerAge;
	
	@Column(name="PlayerAddress")	//column name
	private String playerAddress;
	
	@Column(name="playerCategory")	//column name
	private String playerCategory;

	//creating the getter and setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPlayerFirstName() {
		return playerFirstName;
	}

	public void setPlayerFirstName(String playerFirstName) {
		this.playerFirstName = playerFirstName;
	}

	public String getPlayerLastName() {
		return playerLastName;
	}

	public void setPlayerLastName(String playerLastName) {
		this.playerLastName = playerLastName;
	}

	public int getPlayerAge() {
		return playerAge;
	}

	public void setPlayerAge(int playerAge) {
		this.playerAge = playerAge;
	}

	public String getPlayerAddress() {
		return playerAddress;
	}

	public void setPlayerAddress(String playerAddress) {
		this.playerAddress = playerAddress;
	}

	public String getPlayerCategory() {
		return playerCategory;
	}

	public void setPlayerCategory(String playerCategory) {
		this.playerCategory = playerCategory;
	}
}
