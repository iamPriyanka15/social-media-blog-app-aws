package com.priyanka.learning.socialmediablogapp.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class BlogApIException extends RuntimeException{

    private HttpStatus status;
    private String message;

}
