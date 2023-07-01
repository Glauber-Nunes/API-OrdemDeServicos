package com.gnsoftware.Ordem.Servico.controller.exceptions;

import lombok.Data;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
public class ValidationError extends StandardError {

    private List<FieldMessage> fieldMessages = new ArrayList<>();


    public ValidationError(Instant timestamp, int status, String Error,String path) {
        super(timestamp, status, Error,path);
    }

    public ValidationError() {
        super();
    }

    public void addErro(String fieldName, String message) {
        this.fieldMessages.add(new FieldMessage(fieldName, message));
    }
}
