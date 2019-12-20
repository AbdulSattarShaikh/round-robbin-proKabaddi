package com.intelliSwift.proKabaddi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intelliSwift.proKabaddi.dtos.TeamDto;
import com.intelliSwift.proKabaddi.exceptions.BadRequestException;
import com.intelliSwift.proKabaddi.service.TeamService;

@RestController
@RequestMapping(path = "/prokabadi/team")
public class TeamController {
	
	@Autowired
	  private TeamService teamService;
	 
	
	  @PostMapping(path = "/add", consumes="application/json", produces="application/json")
	  public ResponseEntity<TeamDto> add( @RequestBody TeamDto team ) throws BadRequestException
	  {
	    return new ResponseEntity<>(teamService.add(team),HttpStatus.CREATED);
	  }
	  
	 
	  @PostMapping(path="/add/all", consumes="application/json", produces="application/json")
	  public ResponseEntity<List<TeamDto>> addAll(@RequestBody List<TeamDto> teamList) throws BadRequestException
	  {
	     return new ResponseEntity<>(teamService.add(teamList),HttpStatus.CREATED);
	  }

}
