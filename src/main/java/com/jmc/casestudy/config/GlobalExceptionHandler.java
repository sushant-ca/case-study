package com.jmc.casestudy.config;

import com.jmc.casestudy.payload.response.ErrorResponse;
import java.util.Date;
import java.util.HashMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public final ResponseEntity<ErrorResponse> handleInvalidExceptions(
      MethodArgumentNotValidException methodArgumentNotValidException) {
    HashMap<String, String> errors = new HashMap<>();
    methodArgumentNotValidException
        .getBindingResult()
        .getAllErrors()
        .forEach(
            error -> {
              String fieldName = ((FieldError) error).getField();
              String errorMessage = error.getDefaultMessage();
              errors.put(fieldName, errorMessage);
            });
    ErrorResponse exceptionResponse = new ErrorResponse(errors.toString(), new Date());
    return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleValidationErrors(Exception ex) {
    return new ResponseEntity<>(
        new ErrorResponse(ex.getMessage(), new Date()), HttpStatus.BAD_REQUEST);
  }
}
