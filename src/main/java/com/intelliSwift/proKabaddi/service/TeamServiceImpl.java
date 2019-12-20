package com.intelliSwift.proKabaddi.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intelliSwift.proKabaddi.dtos.TeamDto;
import com.intelliSwift.proKabaddi.entity.Team;
import com.intelliSwift.proKabaddi.repositories.TeamRepository;


@Service
public class TeamServiceImpl implements TeamService {

	@Autowired
	private TeamRepository teamRepository;

	ModelMapper mapper = new ModelMapper();

	@Override
	public TeamDto add(TeamDto model) {
		
		
		Team entity = mapper.map(model, Team.class);
		teamRepository.save(entity);
		return mapper.map(entity, TeamDto.class);
	}

	@Override
	public List<TeamDto> add(List<TeamDto> teamList) {
		teamList.forEach(team -> add(team));
		return teamList;
	}

}
