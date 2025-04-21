package com.sportsmanagement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AllTeamsDto {
	
	private Long teamId;
	private String teamName;
	private String coachName;
	private String logo;
	private String owner;

}
