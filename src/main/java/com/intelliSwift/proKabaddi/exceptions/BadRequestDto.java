package com.intelliSwift.proKabaddi.exceptions;

public class BadRequestDto {

	private String message;

	public BadRequestDto() {

	}

	public BadRequestDto(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
