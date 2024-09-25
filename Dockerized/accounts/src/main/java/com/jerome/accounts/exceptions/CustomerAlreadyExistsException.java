package com.jerome.accounts.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// When this exception is thrown, the response will return 400, BAD REQUEST
@ResponseStatus(HttpStatus.BAD_REQUEST)
// extending to the runtimeException, it becomes unchecked exception meaning it does not need throws clause
public class CustomerAlreadyExistsException extends RuntimeException{

    // A constructor that accepts string which is passed to the superclass(RuntimeException)
    public CustomerAlreadyExistsException(String message){
        super(message);
    }
}
