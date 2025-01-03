package br.com.gabrielcaiodev.manytoone_onetomany.controller.handlers;

import br.com.gabrielcaiodev.manytoone_onetomany.controller.error.ErrorMessage;
import br.com.gabrielcaiodev.manytoone_onetomany.controller.error.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExeceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorMessage err = new ErrorMessage(Instant.now(),status.value(), e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}

