package com.sportsmanagement.util;

import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

import com.sportsmanagement.dto.AllTeamsDto;
import com.sportsmanagement.dto.PlayerDto;
import com.sportsmanagement.dto.PlayerSummaryDto;
import com.sportsmanagement.dto.TeamDto;
import com.sportsmanagement.dto.TeamNameDto;
import com.sportsmanagement.dto.TeamSummaryDto;
import com.sportsmanagement.entities.Player;
import com.sportsmanagement.entities.Team;
import com.sportsmanagement.enums.BattingStyle;
import com.sportsmanagement.enums.BowlingStyle;
import com.sportsmanagement.enums.Role;

@Component
public class UtilConverter {

	public PlayerDto toPlayerDto(Player player, TeamNameDto teamDto) {
	    PlayerDto playerDto = new PlayerDto();
	    playerDto.setPlayerId(player.getPlayerId());
	    playerDto.setFirstName(player.getFirstName());
	    playerDto.setLastName(player.getLastName());
	    playerDto.setDateOfBirth(player.getDateOfBirth());
	    playerDto.setRole(player.getRole() !=null ? player.getRole().toString() : null);
	    playerDto.setNationality(player.getNationality());

	    // ✅ Convert Enum to String (Handle null case)
	    playerDto.setBattingStyle(player.getBattingStyle() != null ? player.getBattingStyle().toString() : null);
	    playerDto.setBowlingStyle(player.getBowlingStyle() != null ? player.getBowlingStyle().toString() : null);

	    playerDto.setTeam(teamDto);
	    return playerDto;
	}

	public Player toPlayerEntity(PlayerDto playerDto, Team team) {
	    Player player = new Player();
	    player.setPlayerId(playerDto.getPlayerId());
	    player.setFirstName(playerDto.getFirstName());
	    player.setLastName(playerDto.getLastName());
	    player.setDateOfBirth(playerDto.getDateOfBirth());
	    if ((playerDto.getRole() != null && !playerDto.getRole().isEmpty())) {
	    	try {
				player.setRole(Role.valueOf(playerDto.getRole().toUpperCase()));
			} catch (IllegalArgumentException e) {
				throw new RuntimeException("Invalid player role: " + playerDto.getRole());
			}
			
		}
	    
	    player.setNationality(playerDto.getNationality());

	    // ✅ Convert String to Enum safely
	    if (playerDto.getBattingStyle() != null && !playerDto.getBattingStyle().isEmpty()) {
	        try {
	            player.setBattingStyle(BattingStyle.valueOf(playerDto.getBattingStyle().toUpperCase()));
	        } catch (IllegalArgumentException e) {
	            throw new RuntimeException("Invalid Batting Style: " + playerDto.getBattingStyle());
	        }
	    }

	    if (playerDto.getBowlingStyle() != null && !playerDto.getBowlingStyle().isEmpty()) {
	        try {
	            player.setBowlingStyle(BowlingStyle.valueOf(playerDto.getBowlingStyle().toUpperCase()));
	        } catch (IllegalArgumentException e) {
	            throw new RuntimeException("Invalid Bowling Style: " + playerDto.getBowlingStyle());
	        }
	    }

	    player.setTeam(team);
	    return player;
	}

	public PlayerSummaryDto toPlayerSummaryDto(Player player) {
	    PlayerSummaryDto playerSummaryDto = new PlayerSummaryDto();
	    playerSummaryDto.setPlayerId(player.getPlayerId());
	    playerSummaryDto.setFirstName(player.getFirstName());
	    playerSummaryDto.setLastName(player.getLastName());
	    playerSummaryDto.setDateOfBirth(player.getDateOfBirth());
	    playerSummaryDto.setRole(player.getRole() !=null ? player.getRole().toString() : null);
	    playerSummaryDto.setNationality(player.getNationality());

	    // ✅ Convert Enum to String safely
	    playerSummaryDto.setBattingStyle(player.getBattingStyle() != null ? player.getBattingStyle().toString() : null);
	    playerSummaryDto.setBowlingStyle(player.getBowlingStyle() != null ? player.getBowlingStyle().toString() : null);

	    return playerSummaryDto;
	}

	public TeamDto toTeamDto(Team team) {
		TeamDto teamDto = new TeamDto();
		teamDto.setTeamId(team.getTeamId());
		teamDto.setTeamName(team.getTeamName());
		teamDto.setCoachName(team.getCoachName());
		teamDto.setLogo(team.getLogo());
		teamDto.setOwner(team.getOwner());
		if (team.getPlayers() != null) {
			teamDto.setPlayers(
					team.getPlayers().stream().map(player -> toPlayerDto(player, null)).collect(Collectors.toList()));
		}
		return teamDto;
	}

	public Team toTeamEntity(TeamDto teamDto) {
		Team team = new Team();
		team.setTeamId(teamDto.getTeamId());
		team.setTeamName(teamDto.getTeamName());
		team.setCoachName(teamDto.getCoachName());
		team.setLogo(teamDto.getLogo());
		team.setOwner(teamDto.getOwner());
		if (teamDto.getPlayers() != null) {
			team.setPlayers(teamDto.getPlayers().stream().map(playerDto -> toPlayerEntity(playerDto, team))
					.collect(Collectors.toList()));
		}
		return team;
	}
	
	// Convert Team entity to TeamSummaryDto (with summarized players)
    public TeamSummaryDto toTeamSummaryDto(Team team) {
        TeamSummaryDto teamSummaryDto = new TeamSummaryDto();
        teamSummaryDto.setTeamId(team.getTeamId());
        teamSummaryDto.setTeamName(team.getTeamName());
        teamSummaryDto.setCoachName(team.getCoachName());
        teamSummaryDto.setLogo(team.getLogo());
        teamSummaryDto.setOwner(team.getOwner());

        // Convert players to PlayerSummaryDto (no team field)
        if (team.getPlayers() != null) {
            teamSummaryDto.setPlayers(
                    team.getPlayers().stream().map(this::toPlayerSummaryDto).collect(Collectors.toList()));
        }

        return teamSummaryDto;
    }
    
    public AllTeamsDto toAllTeamDto(Team team) {
    	AllTeamsDto allTeamsDto = new AllTeamsDto();
    	allTeamsDto.setTeamId(team.getTeamId());
    	allTeamsDto.setTeamName(team.getTeamName());
    	allTeamsDto.setCoachName(team.getCoachName());
    	allTeamsDto.setLogo(team.getLogo());
    	allTeamsDto.setOwner(team.getOwner());
    	
    	return allTeamsDto;
    }
    
    public TeamNameDto toTeamNameDto(Team team) {
    	TeamNameDto teamNameDto = new TeamNameDto();
    	teamNameDto.setTeamId(team.getTeamId());
    	teamNameDto.setTeamName(team.getTeamName());
        return teamNameDto;
    }

}
