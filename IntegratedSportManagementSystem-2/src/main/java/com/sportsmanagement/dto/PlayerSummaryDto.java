package com.sportsmanagement.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerSummaryDto {

	private Long playerId;
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private String role;
	private String nationality;
	private String battingStyle;
	private String bowlingStyle;
}
