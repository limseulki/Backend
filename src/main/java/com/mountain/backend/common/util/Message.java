package com.mountain.backend.common.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Message {

    private int statusCode;
    private String message;
    private Object data;

    public static Message setSuccess(StatusEnum statusEnum, String message, Object data) {
        return new Message(statusEnum.getStatus().value(), message, data);
    }
}
