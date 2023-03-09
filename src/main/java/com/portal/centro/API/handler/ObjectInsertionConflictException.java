package com.portal.centro.API.handler;

public class ObjectInsertionConflictException extends RuntimeException{
    private String message;

    public ObjectInsertionConflictException(String message){
        super(message);
    }
}