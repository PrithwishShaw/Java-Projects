package com.sportsmanagement.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
public class PlayerDto {

	private Long playerId;
	
	@NotEmpty(message = "Player first name cannot be empty. Please provide a valid first name.")
	@Size(min = 2, max = 20, message = "The length will be more than 1 characters")
	private String firstName;
	
	@NotEmpty(message = "Player last name cannot be empty. Please provide a valid last name.")
	@Size(min = 2, max = 20, message = "The length will be more than 1 characters")
	private String lastName;
	
	@NotNull(message = "Birth date cannot be empty. Please provide a valid birth date.")
	private LocalDate dateOfBirth;
	
	@NotEmpty(message = "Player role is required")
	@Pattern(regexp = "BATSMAN|BOWLER|BATTING_ALLROUNDER|BOWLING_ALLROUNDER|WICKET_KEEPER", message = "Invalid player role")
	private String role;
	
	@NotEmpty(message = "Nationality cannot be empty. Please provide a valid nationality.")
	private String nationality;
	
	@NotEmpty(message = "Batting style is required")
    @Pattern(regexp = "RIGHT_HANDED_BAT|LEFT_HANDED_BAT", message = "Invalid batting style")
	private String battingStyle;
	
	@NotEmpty(message = "Bowling style is required")
    @Pattern(regexp = "RIGHT_ARM_FAST|RIGHT_ARM_MEDIUM|RIGHT_ARM_OFF_SPIN|RIGHT_ARM_LEG_SPIN|LEFT_ARM_FAST|LEFT_ARM_MEDIUM|LEFT_ARM_ORTHODOX|LEFT_ARM_CHINAMAN|NA",
             message = "Invalid bowling style")
	private String bowlingStyle;
	
	private TeamNameDto team;

}
