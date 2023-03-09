package com.portal.centro.API.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFound extends RuntimeException{
    private String message;

    public UserNotFound(String message){
        super(message);
    }
}