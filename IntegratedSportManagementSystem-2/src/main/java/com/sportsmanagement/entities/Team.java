package com.sportsmanagement.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "teams")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "team_id")
	private Long teamId;

	@Column(name = "team_name")
	private String teamName;

	@Column(name = "coach", nullable = false)
	private String coachName;

	@Column(name = "logo")
	private String logo;

	@Column(name = "owner", nullable = false)
	private String owner;
	
	@OneToMany(mappedBy = "team",cascade = CascadeType.REMOVE)
	private List<Player> players;

}
