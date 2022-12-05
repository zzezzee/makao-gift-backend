package com.zzezze.makaogift.advices;

import com.zzezze.makaogift.exceptions.AuthenticationError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AuthenticationErrorAdvice {
    @ResponseBody
    @ExceptionHandler(AuthenticationError.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String authenticationError() {
        return "AuthenticationError";
    }
}
