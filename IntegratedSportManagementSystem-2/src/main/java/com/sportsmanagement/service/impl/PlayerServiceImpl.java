package com.sportsmanagement.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sportsmanagement.dto.PlayerDto;
import com.sportsmanagement.dto.PlayerSummaryDto;
import com.sportsmanagement.dto.TeamNameDto;
import com.sportsmanagement.entities.Player;
import com.sportsmanagement.entities.Team;
import com.sportsmanagement.enums.BattingStyle;
import com.sportsmanagement.enums.BowlingStyle;
import com.sportsmanagement.enums.Role;
import com.sportsmanagement.exception.PlayerNotFoundException;
import com.sportsmanagement.exception.PlayersNotFoundWithRole;
import com.sportsmanagement.exception.TeamNotFoundException;
import com.sportsmanagement.repository.PlayerRepository;
import com.sportsmanagement.repository.TeamRepository;
import com.sportsmanagement.service.PlayerService;
import com.sportsmanagement.util.UtilConverter;

@Service
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	private UtilConverter converterUtil;

	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private TeamRepository teamRepository;

	@Override
	public PlayerDto addPlayer(PlayerDto playerDto) {
		Team team = null;
		TeamNameDto teamDto = null;

		// Fetch the team if a team ID is provided in PlayerDto
		if (playerDto.getTeam() != null && playerDto.getTeam().getTeamId() != null) {
			team = teamRepository.findById(playerDto.getTeam().getTeamId()).orElseThrow(
					() -> new RuntimeException("Team not found with ID: " + playerDto.getTeam().getTeamId()));
			// Convert the Team entity to TeamDto
			teamDto = converterUtil.toTeamNameDto(team);
		}

		// Convert PlayerDto to Player entity with the fetched team
		Player player = converterUtil.toPlayerEntity(playerDto, team);

		// Save the player entity
		player = playerRepository.save(player);

		// Convert saved entity back to DTO, passing the TeamDto
		return converterUtil.toPlayerDto(player, teamDto);
	}

	@Override
	public List<PlayerSummaryDto> getAllPlayers() {
		List<Player> players = playerRepository.findAll();

		return players.stream().map(converterUtil::toPlayerSummaryDto).collect(Collectors.toList());
	}

	@Override
	public PlayerDto getPlayerById(Long playerId) {

		Player player = playerRepository.findPlayerById(playerId);
		if (player == null) {
			throw new PlayerNotFoundException("Player not found with ID: " + playerId);
		}
		Team team = player.getTeam();
		TeamNameDto teamDto = converterUtil.toTeamNameDto(team);

		return converterUtil.toPlayerDto(player, teamDto);
	}

	@Override
	public PlayerSummaryDto updatePlayer(Long playerId, PlayerDto playerDto) {

		Player player = playerRepository.findPlayerById(playerId);
		if (player == null) {
			throw new PlayerNotFoundException("Player not found with ID: " + playerId);
		} else {
			player.setFirstName(playerDto.getFirstName());
			player.setLastName(playerDto.getLastName());
			player.setDateOfBirth(playerDto.getDateOfBirth());
			if (playerDto.getRole() != null && !playerDto.getRole().isEmpty()) {
				player.setRole(Role.valueOf(playerDto.getRole().toUpperCase()));
			}

			player.setNationality(playerDto.getNationality());

			// Convert String to Enum before setting it
			if (playerDto.getBattingStyle() != null && !playerDto.getBattingStyle().isEmpty()) {
				player.setBattingStyle(BattingStyle.valueOf(playerDto.getBattingStyle().toUpperCase()));
			}

			if (playerDto.getBowlingStyle() != null && !playerDto.getBowlingStyle().isEmpty()) {
				player.setBowlingStyle(BowlingStyle.valueOf(playerDto.getBowlingStyle().toUpperCase()));
			}

			// Update the team ID if a new team is provided
			if (playerDto.getTeam() != null && playerDto.getTeam().getTeamId() != null) {
				Long newTeamId = playerDto.getTeam().getTeamId();
				Team newTeam = teamRepository.findById(newTeamId)
						.orElseThrow(() -> new RuntimeException("Team not found with ID: " + newTeamId));

				player.setTeam(newTeam); // Assign the new team to the player
			}

			player = playerRepository.save(player);

		}

		return converterUtil.toPlayerSummaryDto(player);

	}

	@Override
	public String deletePlayer(Long playerId) {

		String message = null;

		Player player = playerRepository.findPlayerById(playerId);

		if (player != null) {
			playerRepository.deleteById(playerId);
			message = "Deleted";
			return message;
		} else {
			message = "Not Found";
			return message;
		}

	}

	@Override
	public List<PlayerSummaryDto> getPlayersByTeamId(Long teamId) {
		List<Player> players = playerRepository.findByTeamId(teamId);

		if (players.isEmpty()) {
			throw new TeamNotFoundException("No players found for team ID: " + teamId);
		}

		return players.stream().map(converterUtil::toPlayerSummaryDto).collect(Collectors.toList());
	}

	@Override
	public List<PlayerSummaryDto> getPlayersByRole(String role) {
		Role playerRole;

		try {
			playerRole = Role.valueOf(role.toUpperCase()); // Convert string to enum
		} catch (IllegalArgumentException e) {
			throw new PlayersNotFoundWithRole("Invalid player role: " + role);
		}

		List<Player> players = playerRepository.findByRole(playerRole);

		if (players.isEmpty()) {
			throw new PlayersNotFoundWithRole("No players found with role: " + role);
		}

		return players.stream().map(converterUtil::toPlayerSummaryDto).collect(Collectors.toList());
	}

	@Override
	public List<PlayerSummaryDto> searchPlayers(String keyword) {
		List<Player> players = playerRepository.searchPlayers(keyword);

	    return players.stream()
	                  .map(converterUtil::toPlayerSummaryDto)
	                  .collect(Collectors.toList());
	}

	@Override
	public String bulkUploadPlayers(MultipartFile file) {
	    List<Player> players = new ArrayList<>();
	    List<String> errors = new ArrayList<>();

	    try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
	        String line;
	        boolean isFirstLine = true;
	        int rowNumber = 1;

	        while ((line = reader.readLine()) != null) {
	            if (isFirstLine) {
	                isFirstLine = false;
	                continue;
	            }
	            rowNumber++;

	            String[] data = line.split(",");
	            if (data.length < 8) {
	                errors.add("Row " + rowNumber + ": Incomplete data.");
	                continue;
	            }

	            try {
	                Player player = new Player();
	                player.setFirstName(data[0].trim());
	                player.setLastName(data[1].trim());
	                player.setDateOfBirth(LocalDate.parse(data[2].trim()));

	                try {
	                    player.setRole(Role.valueOf(data[3].trim().toUpperCase()));
	                } catch (IllegalArgumentException e) {
	                    throw new RuntimeException("Invalid role: " + data[3]);
	                }

	                player.setNationality(data[4].trim());

	                try {
	                    player.setBattingStyle(BattingStyle.valueOf(data[5].trim().toUpperCase()));
	                } catch (IllegalArgumentException e) {
	                    throw new RuntimeException("Invalid batting style: " + data[5]);
	                }

	                try {
	                    player.setBowlingStyle(BowlingStyle.valueOf(data[6].trim().toUpperCase()));
	                } catch (IllegalArgumentException e) {
	                    throw new RuntimeException("Invalid bowling style: " + data[6]);
	                }

	                Long teamId = Long.parseLong(data[7].trim());
	                Team team = teamRepository.findById(teamId)
	                        .orElseThrow(() -> new RuntimeException("Team ID not found: " + teamId));
	                player.setTeam(team);

	                players.add(player);
	            } catch (Exception e) {
	                errors.add("Row " + rowNumber + ": " + e.getMessage());
	            }
	        }

	        if (!players.isEmpty()) {
	            playerRepository.saveAll(players);
	        }

	        String summary = "Upload complete. " + players.size() + " players saved.";
	        if (!errors.isEmpty()) {
	            summary += " Errors: \n" + String.join("\n", errors);
	        }

	        return summary;

	    } catch (IOException e) {
	        throw new RuntimeException("Failed to read file: " + e.getMessage());
	    }
	}

}
