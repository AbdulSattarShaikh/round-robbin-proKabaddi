package com.intelliSwift.proKabaddi.dtos;

import java.time.LocalDateTime;

public class MatchDto {

	
	private static final long serialVersionUID = 1L;
	private long id;
	private long homeTeamId;
	private long awayTeamId;
	private LocalDateTime matchDate;
	private String location;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getHomeTeamId() {
		return homeTeamId;
	}

	public void setHomeTeamId(long homeTeamId) {
		this.homeTeamId = homeTeamId;
	}

	public long getAwayTeamId() {
		return awayTeamId;
	}

	public void setAwayTeamId(long awayTeamId) {
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	public boolean isTeamMatch(MatchDto other) {
		return (other != null && other instanceof MatchDto)
				&& (this.homeTeamId == other.homeTeamId || this.homeTeamId == other.awayTeamId
						|| this.awayTeamId == other.awayTeamId || this.awayTeamId == other.homeTeamId);
	}

	
	


}
