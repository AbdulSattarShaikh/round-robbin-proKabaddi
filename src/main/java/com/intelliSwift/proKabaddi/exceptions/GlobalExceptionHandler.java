package com.intelliSwift.proKabaddi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;


public class GlobalExceptionHandler {	
	
	
	
		
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<BadRequestDto> handlBadRequestException(BadRequestException exception) {
	
		BadRequestDto dto = new BadRequestDto("The provided data contains invalid values");

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
	}

	@ExceptionHandler({ RuntimeException.class, Exception.class })
	public ResponseEntity<ErrorResponseDto> handleException(Exception exception) {
	
		ErrorResponseDto dto = new ErrorResponseDto("Something went wrong");

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(dto);
	}

}
