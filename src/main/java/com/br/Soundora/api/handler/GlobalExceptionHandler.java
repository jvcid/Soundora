package com.br.Soundora.api.handler;

import com.br.Soundora.core.exception.EntityNotFoundException;
import java.time.Instant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler({EntityNotFoundException.class, BadCredentialsException.class})
  public ResponseEntity<StandardError> handleEntityNotFound(
      RuntimeException e, WebRequest request) {
    HttpStatus status = HttpStatus.NOT_FOUND;

    StandardError error =
        new StandardError(
            Instant.now(),
            status.value(),
            "Entity not found error",
            e.getMessage(),
            request.getDescription(false).replace("uri=", ""));

    return ResponseEntity.status(status).body(error);
  }
}
