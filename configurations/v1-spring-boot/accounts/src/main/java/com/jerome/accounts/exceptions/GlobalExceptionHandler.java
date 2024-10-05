package com.jerome.accounts.exceptions;

import com.jerome.accounts.dto.ErrorResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// This annotation makes the class a global exception handler across all controllers in the application
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // We override this method inside the ResponseEntityExceptionHandler
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        // We created logic

        // Creating a map values to store the field and error message
        Map<String, String> errors = new HashMap<>();
        // retrieves all validation errors from the bindingResult inside the MethodArgumentNotValidException
        List<ObjectError> errorList = ex.getBindingResult().getAllErrors();

        // handles field-specific validation errors
        errorList.forEach((error) -> {
            // casting error to FieldError to get the cause of error (field)
            String fieldName = ((FieldError)error).getField();
            // gets the error message that we handle in the DTOs
            String validationMessage = error.getDefaultMessage();
            // inserts the values in our hashMap
            errors.put(fieldName, validationMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


    // handles global exception (if my pre define exception is not thrown, this exception will handle it)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> globalExceptionHandler(Exception exception,
                                                                   WebRequest request){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                request.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }



    // this annotation calls this method whenever CustomerAlreadyExistException is thrown anywhere in application
    @ExceptionHandler(CustomerAlreadyExistsException.class)
    // passing the custom error response dto
    public ResponseEntity<ErrorResponseDto> handleException(CustomerAlreadyExistsException exception,
                                                            WebRequest request) {
        // passing the values of the error response dto
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                request.getDescription(false),
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFound(ResourceNotFoundException exception,
                                                                   WebRequest request) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                request.getDescription(false),
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<> (errorResponseDto, HttpStatus.NOT_FOUND);
    }
}
