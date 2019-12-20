package com.intelliSwift.proKabaddi.service;

import java.time.LocalDateTime;
import java.util.List;

import com.intelliSwift.proKabaddi.dtos.MatchDto;

public interface MatchScheduleService {

	public List<MatchDto> initStartDateAndGenerateSchedule(LocalDateTime startDateTime);

}
