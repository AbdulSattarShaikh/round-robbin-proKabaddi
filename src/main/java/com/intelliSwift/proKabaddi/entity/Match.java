package com.intelliSwift.proKabaddi.entity;

import java.time.LocalDateTime;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.intelliSwift.proKabaddi.util.DateTimeConverter;

@Entity(name = "kabddi_match")
public class Match {	
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private Long id;
	  private Long homeTeamId;
	  private Long awayTeamId;
	  
	  @Convert(converter=DateTimeConverter.class)
	  private LocalDateTime matchDate;	  
	  
	  private String location;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getHomeTeamId() {
		return homeTeamId;
	}

	public void setHomeTeamId(Long homeTeamId) {
		this.homeTeamId = homeTeamId;
	}

	public Long getAwayTeamId() {
		return awayTeamId;
	}

	public void setAwayTeamId(Long awayTeamId) {
		this.awayTeamId = awayTeamId;
	}

	public LocalDateTime getMatchDate() {
		return matchDate;
	}

	public void setMatchDate(LocalDateTime matchDate) {
		this.matchDate = matchDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	  
	  
	  
}
