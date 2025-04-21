package com.sportsmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sportsmanagement.dto.PlayerDto;
import com.sportsmanagement.dto.PlayerSummaryDto;
import com.sportsmanagement.service.PlayerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/player")
public class PlayerController {

	@Autowired
	private PlayerService playerService;

	@PostMapping
	public ResponseEntity<PlayerDto> addPlayer(@Valid @RequestBody PlayerDto playerDto) {

		PlayerDto addPlayerDto = playerService.addPlayer(playerDto);

		return new ResponseEntity<>(addPlayerDto, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<PlayerSummaryDto>> getPlayers() {

		List<PlayerSummaryDto> players = playerService.getAllPlayers();

		return new ResponseEntity<>(players, HttpStatus.OK);

	}

	@GetMapping("/{playerId}")
	public ResponseEntity<PlayerDto> getPlayerById(@PathVariable Long playerId) {

		PlayerDto player = playerService.getPlayerById(playerId);

		return new ResponseEntity<>(player, HttpStatus.FOUND);

	}

	@PutMapping("/{playerId}")
	public ResponseEntity<PlayerSummaryDto> updatePlayer(@PathVariable Long playerId,
			@RequestBody PlayerDto playerDto) {

		PlayerSummaryDto player = playerService.updatePlayer(playerId, playerDto);

		return new ResponseEntity<>(player, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{playerId}")
	public ResponseEntity<?> deletePlayer(@PathVariable Long playerId) {

		String message = playerService.deletePlayer(playerId);

		if (message.equals("Deleted")) {
			return new ResponseEntity<>("Player with ID: " + playerId + " is deleted", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Player not found with ID: " + playerId, HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/team/{teamId}")
	public ResponseEntity<List<PlayerSummaryDto>> getPlayersByTeamId(@PathVariable Long teamId) {
		List<PlayerSummaryDto> players = playerService.getPlayersByTeamId(teamId);
		return ResponseEntity.ok(players);
	}
	
	@GetMapping("/role/{role}")
	public ResponseEntity<List<PlayerSummaryDto>> getPlayersByRole(@PathVariable String role) {
	    List<PlayerSummaryDto> players = playerService.getPlayersByRole(role);
	    return ResponseEntity.ok(players);
	}

	@GetMapping("/search")
	public ResponseEntity<?> searchPlayers(@RequestParam String keyword) {
	    List<PlayerSummaryDto> result = playerService.searchPlayers(keyword);

	    if (result.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                             .body("Not Found");
	    }

	    return ResponseEntity.ok(result);
	}
	
	@PostMapping("/bulk-upload")
	public ResponseEntity<String> uploadPlayers(@RequestParam("file") MultipartFile file) {
	    try {
	        String message = playerService.bulkUploadPlayers(file);
	        return ResponseEntity.ok(message);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload players: " + e.getMessage());
	    }
	}
}
