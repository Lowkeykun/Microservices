package com.jerome.loans.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class LoanAlreadyExistingException extends RuntimeException{

    public LoanAlreadyExistingException(String message){
        super(message);
    }
}
