package com.sportsmanagement.entities;

import java.time.LocalDate;

import com.sportsmanagement.enums.BattingStyle;
import com.sportsmanagement.enums.BowlingStyle;
import com.sportsmanagement.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "players")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "player_id")
	private Long playerId;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "date_of_birth", nullable = false)
	private LocalDate dateOfBirth;

	@Column(name = "role", nullable = false)
	@Enumerated(EnumType.STRING)
	private Role role;

	@Column(name = "nationality", nullable = false)
	private String nationality;

	@Column(name = "batting_style")
	@Enumerated(EnumType.STRING)
	private BattingStyle battingStyle;

	@Column(name = "bowling_style")
	@Enumerated(EnumType.STRING)
	private BowlingStyle bowlingStyle;
	
	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;

}
