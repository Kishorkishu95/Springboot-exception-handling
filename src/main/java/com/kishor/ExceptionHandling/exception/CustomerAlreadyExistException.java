package com.kishor.ExceptionHandling.exception;

import org.springframework.http.HttpStatus;

public class CustomerAlreadyExistException extends RuntimeException{
    private String message;
    public CustomerAlreadyExistException(){}

    public CustomerAlreadyExistException(String msg){
        super(msg);
        this.message=msg;
    }
}
