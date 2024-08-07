package com.killuacode.Todoapi.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class VerificationTokenExpiredException extends RuntimeException{

    public VerificationTokenExpiredException(String message) {
        super(message);
    }
}
