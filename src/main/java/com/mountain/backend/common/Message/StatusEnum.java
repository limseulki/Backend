package com.mountain.backend.common.Message;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEnum {

	OK(HttpStatus.OK, "OK");
	private final HttpStatus status;
	private final String message;

}