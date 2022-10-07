package com.example.demo.qerja.exception;

public class InvalidTokenException extends RuntimeException {

  public InvalidTokenException() {
    super("Invalid Token");
  }
}