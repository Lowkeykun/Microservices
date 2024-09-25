package com.jerome.accounts.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// This ensures whenever this exception is thrown, http response will respond 404 Not Found status code
@ResponseStatus(HttpStatus.NOT_FOUND)
// this class extends the runtimeException so that we do not need to declare throws clause or try-catch block
public class ResourceNotFoundException extends RuntimeException{

    // Constructor where message will indicate which resource wasn't found and the specific field and value
    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue){

        // using super(message) to pass up the error message to the parent class which is RuntimeException
        super(String.format("Resource %s not found in the given input field %s : %s", resourceName, fieldName, fieldValue));
    }
}
