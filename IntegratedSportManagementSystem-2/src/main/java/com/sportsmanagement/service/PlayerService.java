package com.sportsmanagement.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.sportsmanagement.dto.PlayerDto;
import com.sportsmanagement.dto.PlayerSummaryDto;

public interface PlayerService {

	public PlayerDto addPlayer(PlayerDto playerDto);
	public List<PlayerSummaryDto> getAllPlayers();
	public PlayerDto getPlayerById(Long playerId);
	public PlayerSummaryDto updatePlayer(Long playerId ,PlayerDto playerDto);
	public String deletePlayer(Long playerId);
	public List<PlayerSummaryDto> getPlayersByTeamId(Long teamId);
	public List<PlayerSummaryDto> getPlayersByRole(String role);
	public List<PlayerSummaryDto> searchPlayers(String keyword);
	public String bulkUploadPlayers(MultipartFile file);

}
