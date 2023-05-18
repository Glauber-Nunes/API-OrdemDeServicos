package com.gnsoftware.Ordem.Servico.controller.exceptions;

import com.gnsoftware.Ordem.Servico.services.exceptions.ModelNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
                .messenger(notFoundException.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

    }
}
