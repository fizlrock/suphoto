package dev.fizlrock.repositories.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import dev.fizlrock.domain.exception.EventNotFoundException;
import dev.fizlrock.domain.exception.UserNotFoundException;

@ControllerAdvice
public class DefaultAdvice {

  @ExceptionHandler({ UserNotFoundException.class, EventNotFoundException.class })
  public ResponseEntity<String> handleException(Exception e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
  }

}
