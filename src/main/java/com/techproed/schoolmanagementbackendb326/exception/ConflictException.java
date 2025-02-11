package com.techproed.schoolmanagementbackendb326.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ConflictException extends RuntimeException{

    public ConflictException(String messsage) {
        super(messsage);
    }
}
