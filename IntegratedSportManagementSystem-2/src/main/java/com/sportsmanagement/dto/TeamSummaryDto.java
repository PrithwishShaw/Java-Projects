package com.sportsmanagement.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamSummaryDto {
	
	private Long teamId;
	private String teamName;
	private String coachName;
	private String logo;
	private String owner;
	private List<PlayerSummaryDto> players;

}
