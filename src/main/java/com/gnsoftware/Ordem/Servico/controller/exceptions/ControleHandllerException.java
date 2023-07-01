package com.gnsoftware.Ordem.Servico.controller.exceptions;

import com.gnsoftware.Ordem.Servico.services.exceptions.DataIntegrityViolationException;
import com.gnsoftware.Ordem.Servico.services.exceptions.ModelNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ControleHandllerException {

    @ExceptionHandler(ModelNotFound.class)
    public ResponseEntity<StandardError> notFoundException(ModelNotFound notFoundException, HttpServletRequest request) {

        StandardError error = StandardError.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.NOT_FOUND.value())
                .Error("NOT FOUND EXCEPTION")
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validationError(MethodArgumentNotValidException m, HttpServletRequest request) {

        ValidationError validationError = new ValidationError(
                Instant.now(), HttpStatus.BAD_REQUEST.value(), "ERRO NA VALIDAÇAO DOS CAMPOS",
                request.getRequestURI()
        );

        for (FieldError fieldError : m.getBindingResult().getFieldErrors()) {
            validationError.addErro(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> dataIntegrityViolation(DataIntegrityViolationException dataIntegrityViolationException, HttpServletRequest request) {

        StandardError error = StandardError.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .Error(dataIntegrityViolationException.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);

    }
}
