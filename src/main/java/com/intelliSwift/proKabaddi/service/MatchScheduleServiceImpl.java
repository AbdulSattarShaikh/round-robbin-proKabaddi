package com.intelliSwift.proKabaddi.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intelliSwift.proKabaddi.dtos.MatchDto;
import com.intelliSwift.proKabaddi.dtos.TeamDto;
import com.intelliSwift.proKabaddi.entity.Match;
import com.intelliSwift.proKabaddi.entity.Team;
import com.intelliSwift.proKabaddi.repositories.MatchRepository;
import com.intelliSwift.proKabaddi.repositories.TeamRepository;


@Service
public class MatchScheduleServiceImpl implements MatchScheduleService{
	
	
	 @Autowired
	  private TeamRepository teamRepository;
	  @Autowired
	  private MatchRepository matchRepository;
			  
	  ModelMapper mapper = new ModelMapper();
	  
	  private  LocalDateTime startDateTime;
	
	  private  List<MatchDto> generate ( )	  {	    
		  
	    Iterator<Team> homeTeamIterator = teamRepository.findAll().iterator();
	    List<TeamDto> homeTeamList = new ArrayList<>();
	    while ( homeTeamIterator.hasNext() ){
	      homeTeamList.add(mapper.map(homeTeamIterator.next(), TeamDto.class));
	    }
	    
	    List<TeamDto> awayTeamList = new ArrayList<>(homeTeamList);
	    List<MatchDto> matches = new ArrayList<>();
	    
	    
	    for ( TeamDto homeTeam : homeTeamList )
	    {
	      for ( TeamDto awayTeam : awayTeamList )
	      {
	        if ( homeTeam.getId() != awayTeam.getId() )
	        {
	          MatchDto match = new MatchDto();
	          match.setAwayTeamId(awayTeam.getId());
	          match.setHomeTeamId(homeTeam.getId());
	         	                   
	          match.setLocation(homeTeam.getCity());
	          Match matchEntity = mapper.map(match, Match.class);
	         
	          matches.add(mapper.map(matchRepository.save(matchEntity), MatchDto.class));
	        }
	      }
	    }
	   
	    while ( matches.stream().filter(match -> match.getMatchDate() == null).count() != 0 )
	    {
	      List<MatchDto> pendingMatchList = matches.stream().filter(match -> match.getMatchDate() == null)
	          .collect(Collectors.toList());
	      
	      for ( MatchDto match : pendingMatchList )
	      {
	        /**
	         * Collect the matches for localDateTime 
	         */
	        List<MatchDto> currentDayMatchList = matches.stream()
	            .filter(currentDayMatch -> currentDayMatch.getMatchDate() != null
	                && currentDayMatch.getMatchDate().isEqual(startDateTime))
	            .collect(Collectors.toList());
	        
	        /**
	         * Collect the matches for localDateTime - 1  
	         */
	        List<MatchDto> previousMatchList = matches.stream()
	            .filter(previousDayMatch -> previousDayMatch.getMatchDate() != null
	                && previousDayMatch.getMatchDate().isEqual(startDateTime.minusDays(1)))
	            .collect(Collectors.toList());
	        
	        /**
	         *  Max 2 matches per day.
	         *  No team should play on consecutive days
	         */
	        if ( currentDayMatchList.size() < 2
	            && currentDayMatchList.stream().filter(currentMatch -> currentMatch.isTeamMatch(match)).count() == 0
	            && previousMatchList.stream().filter(prevMatch -> prevMatch.isTeamMatch(match)).count() == 0 ){
	            match.setMatchDate(startDateTime);
	            Match matchEntity = mapper.map(match, Match.class);
	            matchRepository.save(matchEntity);
	           
	        }
	      }
	      /**
	       * Let's move to next day
	       */
	      startDateTime = startDateTime.plusDays(1);
	    }
	    
	    /**
	     * sort list based on matchDate
	     */
	    matches.sort(Comparator.comparing(MatchDto::getMatchDate));
	    return matches;
	  }

	  private void initStartDate(LocalDateTime startDateTime)
	  {
	    this.startDateTime = startDateTime;
	  }
	  
	  

	@Override
	public List<MatchDto> initStartDateAndGenerateSchedule(LocalDateTime startDateTime) {	
		initStartDate(startDateTime);
	    return generate();
	}

}
