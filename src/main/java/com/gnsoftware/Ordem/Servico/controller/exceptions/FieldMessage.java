package com.gnsoftware.Ordem.Servico.controller.exceptions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FieldMessage {

    private String fieldName;
    private String message;

    public FieldMessage(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

}
