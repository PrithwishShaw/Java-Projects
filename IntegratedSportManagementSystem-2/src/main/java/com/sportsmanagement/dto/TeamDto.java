package com.sportsmanagement.dto;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
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
public class TeamDto {

	private Long teamId;
	
	@NotEmpty(message = "Team name cannot be empty. Please provide a valid team name.")
	@Size(min = 2, max = 50, message = "The length will be more than 1 characters")
	@Column(name = "team_name", nullable = false, unique = true)
	private String teamName;
	
	@NotEmpty(message = "Coach name cannot be empty. Please provide a valid owner name.")
	@Size(min = 2, max = 20, message = "The length will be more than 1 characters")
	private String coachName;
	private String logo;
	
	@NotEmpty(message = "Owner name cannot be empty. Please provide a valid owner name.")
	@Size(min = 2, max = 20, message = "The length will be more than 1 characters")
	private String owner;
	
	private List<PlayerDto> players;
	

}
