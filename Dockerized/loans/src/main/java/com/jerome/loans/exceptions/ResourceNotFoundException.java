package com.jerome.loans.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message, String fieldName, String fieldValue){
        super(String.format("%s is not at the given value of %s : %s", message, fieldName, fieldValue));
    }
}
