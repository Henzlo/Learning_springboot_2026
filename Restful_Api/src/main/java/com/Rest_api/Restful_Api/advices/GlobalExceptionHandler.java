package com.Rest_api.Restful_Api.advices;
// here we can handle all the exception error which are related to this

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {


    //    how to handle exception and user friendly error handling
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleResourceNotFound(NoSuchElementException exception) {
        return new ResponseEntity<>("Resource not found", HttpStatus.NOT_FOUND);
//        how to handle the httpstatus code
    }
}
