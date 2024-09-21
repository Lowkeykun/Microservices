package com.jerome.cards.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message, String fieldName, String fieldValue){
        super(String.format("%s is not found with the given input data %s : %s", message, fieldName, fieldValue));
    }

}
