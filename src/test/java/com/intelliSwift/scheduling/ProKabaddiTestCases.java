package com.intelliSwift.scheduling;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.intelliSwift.proKabaddi.dtos.TeamDto;
import com.intelliSwift.proKabaddi.service.TeamServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ProKabaddiTestCases {

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private TeamServiceImpl teamService = new TeamServiceImpl();

	@LocalServerPort
	private int port;

	@Test
	public void add() {
		TeamDto team = new TeamDto();
		team.setId(1);
		team.setName("testTeam");
		team.setDescription("TestDiscription");
		team.setCity("testCity");

		HttpEntity<TeamDto> request = new HttpEntity<TeamDto>(team);
		assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/prokabadi/team/add", request,
				TeamDto.class)).isEqualTo(team);
	}

	@Test
	public void addAll() throws JsonParseException, JsonMappingException, IOException {

		List<TeamDto> teamList = new ArrayList<>();

		TeamDto team = new TeamDto();
		team.setId(1);
		team.setName("testTeam");
		team.setDescription("TestDiscription");
		team.setCity("testCity");
		teamList.add(team);

		HttpEntity<List<TeamDto>> dataRequest = new HttpEntity<List<TeamDto>>(teamList);
		ResponseEntity<List<TeamDto>> returnTeamList = restTemplate.exchange(
				"http://localhost:" + port + "/prokabadi/team/add/all", HttpMethod.POST, dataRequest,
				new ParameterizedTypeReference<List<TeamDto>>() {
				});
		assertThat(teamList).isEqualTo(returnTeamList.getBody());

	}

	

}
