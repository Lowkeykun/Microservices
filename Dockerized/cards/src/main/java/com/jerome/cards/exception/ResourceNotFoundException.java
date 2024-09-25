package com.jerome.cards.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message, String fieldName, String fieldValue){
        super(String.format("%s is not found with the given input data %s : %s", message, fieldName, fieldValue));
    }

}
