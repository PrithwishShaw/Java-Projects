package com.sportsmanagement.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.sportsmanagement.dto.AllTeamsDto;
import com.sportsmanagement.dto.TeamDto;
import com.sportsmanagement.dto.TeamNameDto;
import com.sportsmanagement.dto.TeamSummaryDto;

public interface TeamService {

	public TeamNameDto addTeam(TeamDto teamDto, MultipartFile logo);
	public List<TeamSummaryDto> getAllTeamsWithPlayers();
	public List<AllTeamsDto> getAllTeams();
	boolean existsByTeamName(String teamName);
	public byte[] getTeamLogo(Long teamId);
	public String bulkUploadTeamsWithLogos(MultipartFile dataFile, MultipartFile[] logoFiles);


}
