package com.intelliSwift.proKabaddi.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intelliSwift.proKabaddi.dtos.MatchDto;
import com.intelliSwift.proKabaddi.exceptions.BadRequestException;
import com.intelliSwift.proKabaddi.service.MatchScheduleService;

@RestController
@RequestMapping(path = "/prokabadi/schedule")
public class MatchScheduleController {

	@Autowired
	private MatchScheduleService scheduleService;

	@GetMapping({ "/generate", "/generate/{startDate}" })
	public ResponseEntity<List<MatchDto>> generate(@PathVariable Optional<String> startDate) throws BadRequestException {

		LocalDateTime startDateTime = startDate.isPresent()
				? LocalDateTime.parse(startDate.get(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
				: LocalDateTime.now().plusDays(1);
		return new ResponseEntity<>(scheduleService.initStartDateAndGenerateSchedule(startDateTime),
				HttpStatus.ACCEPTED);
	}
}
