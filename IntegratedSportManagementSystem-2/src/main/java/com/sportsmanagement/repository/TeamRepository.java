package com.sportsmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sportsmanagement.entities.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
	
	boolean existsByTeamName(String teamName);

}
