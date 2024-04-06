package com.killuacode.Todoapi.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EmailAlreadyTaken extends RuntimeException{

    public EmailAlreadyTaken(String message) {
        super(message);
    }
}
