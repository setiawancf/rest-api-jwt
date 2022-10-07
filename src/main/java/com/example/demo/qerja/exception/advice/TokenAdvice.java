package com.example.demo.qerja.exception.advice;

import com.example.demo.qerja.exception.InvalidTokenException;
import com.example.demo.qerja.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.security.InvalidAlgorithmParameterException;

@ControllerAdvice
public class TokenAdvice {

  @ResponseBody
  @ExceptionHandler(InvalidTokenException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String invalidToken(InvalidTokenException ex) {
    return ex.getMessage();
  }
}