package com.sportsmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sportsmanagement.entities.Player;
import com.sportsmanagement.enums.Role;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

	@Query("SELECT p FROM Player p WHERE p.id = :playerId")
	Player findPlayerById(@Param("playerId") Long playerId);

	// PlayerRepository.java
	@Query("SELECT p FROM Player p WHERE p.team.teamId = :teamId")
	List<Player> findByTeamId(@Param("teamId") Long teamId);

	// PlayerRepository.java
	List<Player> findByRole(Role role);

	@Query("SELECT p FROM Player p WHERE " + "LOWER(p.firstName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR "
			+ "LOWER(p.lastName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR "
			+ "LOWER(p.nationality) LIKE LOWER(CONCAT('%', :keyword, '%')) OR "
			+ "LOWER(p.role) LIKE LOWER(CONCAT('%', :keyword, '%'))")
	List<Player> searchPlayers(@Param("keyword") String keyword);

}
