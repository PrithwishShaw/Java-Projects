package com.sportsmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sportsmanagement.dto.AllTeamsDto;
import com.sportsmanagement.dto.TeamDto;
import com.sportsmanagement.dto.TeamNameDto;
import com.sportsmanagement.dto.TeamSummaryDto;
import com.sportsmanagement.service.TeamService;


@RestController
@RequestMapping("/api/team")
public class TeamController {

	@Autowired
	private TeamService teamService;

	@PostMapping
    public ResponseEntity<TeamNameDto> addTeam(@RequestPart("teamDto") String teamDtoJson,
    	    @RequestPart(value = "logo", required = false) MultipartFile logo) {

        // Convert JSON string to TeamDto object
        ObjectMapper objectMapper = new ObjectMapper();
        TeamDto teamDto;
        try {
            teamDto = objectMapper.readValue(teamDtoJson, TeamDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Invalid JSON format for teamDto", e);
        }

        TeamNameDto savedTeam = teamService.addTeam(teamDto, logo);
        return new ResponseEntity<>(savedTeam, HttpStatus.CREATED);
	}
	
	@GetMapping("/withPlayers")
	public ResponseEntity<List<TeamSummaryDto>> getAllTeamsWithPlayers() {
	    List<TeamSummaryDto> teamsWithPlayers = teamService.getAllTeamsWithPlayers();
	    return new ResponseEntity<>(teamsWithPlayers, HttpStatus.OK);
	}
	
	@GetMapping()
	public ResponseEntity<List<AllTeamsDto>> getAllTeams() {
	    List<AllTeamsDto> allTeams = teamService.getAllTeams();
	    return new ResponseEntity<>(allTeams, HttpStatus.OK);
	}
	
	@GetMapping("/exists")
	public ResponseEntity<Boolean> existsByTeamName(@RequestParam String teamName) {
	    boolean exists = teamService.existsByTeamName(teamName);
	    return new ResponseEntity<>(exists, HttpStatus.OK);
	}
	
	@GetMapping("/{id}/logo")
	public ResponseEntity<byte[]> getTeamLogo(@PathVariable Long id) {
	    byte[] imageData = teamService.getTeamLogo(id);
	    return ResponseEntity
	            .ok()
	            .contentType(MediaType.IMAGE_JPEG) // or IMAGE_PNG based on your files
	            .body(imageData);
	}
	
	@PostMapping("/bulk-upload")
	public ResponseEntity<String> bulkUploadTeamsWithLogos(
	        @RequestParam("dataFile") MultipartFile dataFile,
	        @RequestParam("logoFiles") MultipartFile[] logoFiles) {
	    
	    String result = teamService.bulkUploadTeamsWithLogos(dataFile, logoFiles);
	    return ResponseEntity.ok(result);
	}
}
