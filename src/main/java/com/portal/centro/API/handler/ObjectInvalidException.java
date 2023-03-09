package com.portal.centro.API.handler;

public class ObjectInvalidException extends RuntimeException{
    private String message;

    public ObjectInvalidException(String message){
        super(message);
    }
}