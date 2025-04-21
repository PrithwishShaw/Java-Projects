package com.sportsmanagement.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import com.sportsmanagement.dto.AllTeamsDto;
import com.sportsmanagement.dto.PlayerSummaryDto;
import com.sportsmanagement.dto.TeamDto;
import com.sportsmanagement.dto.TeamNameDto;
import com.sportsmanagement.dto.TeamSummaryDto;
import com.sportsmanagement.entities.Team;
import com.sportsmanagement.exception.TeamNameAlreadyExistsException;
import com.sportsmanagement.exception.TeamNotFoundException;
import com.sportsmanagement.repository.TeamRepository;
import com.sportsmanagement.service.ImageService;
import com.sportsmanagement.service.TeamService;
import com.sportsmanagement.util.UtilConverter;

@Validated
@Service
public class TeamServiceImpl implements TeamService {

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private UtilConverter converterUtil;

	@Autowired
	private ImageService imageService;

	@Override
	public TeamNameDto addTeam(TeamDto teamDto, MultipartFile logo) {

		// Check if the team name already exists
		if (teamRepository.existsByTeamName(teamDto.getTeamName())) {
			throw new TeamNameAlreadyExistsException("Team name already exists: " + teamDto.getTeamName());
		}

		Team team = converterUtil.toTeamEntity(teamDto);

		// Save the image and set its path
		if (logo != null && !logo.isEmpty()) {
			try {
				String imagePath = imageService.saveImage(logo);
				team.setLogo(imagePath);
			} catch (IOException e) {
				throw new RuntimeException("Failed to save logo image", e);
			}
		}

		team = teamRepository.save(team);
		return converterUtil.toTeamNameDto(team);
	}

	@Override
	public List<TeamSummaryDto> getAllTeamsWithPlayers() {
		List<Team> teams = teamRepository.findAll();

		// Convert teams to TeamSummaryDto
		return teams.stream().map(team -> {
			TeamSummaryDto teamSummaryDto = converterUtil.toTeamSummaryDto(team);
			// Convert players to PlayerSummaryDto (without team details)
			List<PlayerSummaryDto> playerSummaryDtos = team.getPlayers().stream().map(converterUtil::toPlayerSummaryDto)
					.collect(Collectors.toList());
			teamSummaryDto.setPlayers(playerSummaryDtos);
			return teamSummaryDto;
		}).collect(Collectors.toList());
	}

	@Override
	public List<AllTeamsDto> getAllTeams() {

		List<Team> teams = teamRepository.findAll();

		return teams.stream().map(converterUtil::toAllTeamDto).collect(Collectors.toList());

	}

	@Override
	public boolean existsByTeamName(String teamName) {
		return teamRepository.existsByTeamName(teamName);
	}

	@Override
	public byte[] getTeamLogo(Long teamId) {
		Team team = teamRepository.findById(teamId)
		        .orElseThrow(() -> new TeamNotFoundException("Team not found with id: " + teamId));

		    String logoPath = team.getLogo();
		    if (logoPath == null || logoPath.isEmpty()) {
		        throw new RuntimeException("No logo uploaded for team with id: " + teamId);
		    }

		    try {
		        return imageService.getImage(logoPath);
		    } catch (IOException e) {
		        throw new RuntimeException("Failed to read logo image from disk", e);
		    }
	}

	@Override
	public String bulkUploadTeamsWithLogos(MultipartFile dataFile, MultipartFile[] logoFiles) {
	    Map<String, MultipartFile> logoMap = new HashMap<>();
	    for (MultipartFile logo : logoFiles) {
	        String baseName = FilenameUtils.getBaseName(logo.getOriginalFilename());
	        logoMap.put(baseName.toLowerCase(), logo);
	    }

	    List<String> errorMessages = new ArrayList<>();
	    int successCount = 0;

	    try (BufferedReader reader = new BufferedReader(new InputStreamReader(dataFile.getInputStream()))) {
	        String line;
	        boolean isFirstLine = true;

	        while ((line = reader.readLine()) != null) {
	            if (isFirstLine) {
	                isFirstLine = false;
	                continue; // Skip header
	            }

	            String[] data = line.split(",");

	            if (data.length < 4) {
	                errorMessages.add("Incomplete row: " + Arrays.toString(data));
	                continue;
	            }

	            String teamName = data[0].trim();
	            String coachName = data[1].trim();
	            String ownerName = data[2].trim();
	            String imageName = data[3].trim(); // Ex: CSK.png

	            TeamDto teamDto = new TeamDto();
	            teamDto.setTeamName(teamName);
	            teamDto.setCoachName(coachName);
	            teamDto.setOwner(ownerName);

	            try {
	                MultipartFile logo = logoMap.get(FilenameUtils.getBaseName(imageName).toLowerCase());
	                if (logo == null) {
	                    errorMessages.add("Logo file not found for team: " + teamName);
	                    continue;
	                }

	                addTeam(teamDto, logo);
	                successCount++;
	            } catch (TeamNameAlreadyExistsException e) {
	                errorMessages.add("Duplicate team: " + teamName);
	            } catch (Exception e) {
	                errorMessages.add("Error processing team: " + teamName + " - " + e.getMessage());
	            }
	        }

	    } catch (IOException e) {
	        throw new RuntimeException("Failed to read team data file: " + e.getMessage());
	    }

	    return "Teams Uploaded: " + successCount + "\n Errors:\n" + String.join("\n", errorMessages);
	}

}
