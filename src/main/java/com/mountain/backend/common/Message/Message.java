package com.mountain.backend.common.Message;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class Message {
	private int statusCode;
	private String message;
	private Object data;

	public Message(int statusCode, String message){
		this.statusCode = statusCode;
		this.message = message;
	}

	public static Message setSuccess(StatusEnum statusEnum, String message, Object data) {
		return new Message(statusEnum.getStatus().value(), message, data);
	}
}
