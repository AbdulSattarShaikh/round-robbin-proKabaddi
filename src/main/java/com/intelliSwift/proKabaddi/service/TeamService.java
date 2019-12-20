package com.intelliSwift.proKabaddi.service;

import java.util.List;

import com.intelliSwift.proKabaddi.dtos.TeamDto;

public interface TeamService {

	public TeamDto add(TeamDto team);
	public List<TeamDto> add(List<TeamDto> teamList);

	

}
