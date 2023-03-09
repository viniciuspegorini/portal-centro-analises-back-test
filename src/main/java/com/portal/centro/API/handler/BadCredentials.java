package com.portal.centro.API.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadCredentials extends RuntimeException{

    public BadCredentials(String message){
        super(message);
    }
}
