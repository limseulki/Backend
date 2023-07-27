package com.mountain.backend.mountain.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class Message {

	private Object data;

	public Message(Object data) {
		this.data = data;
	}


}