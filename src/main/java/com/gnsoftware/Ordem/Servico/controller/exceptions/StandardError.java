package com.gnsoftware.Ordem.Servico.controller.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StandardError {
    private Instant timestamp;
    private int status;
    private String Error;
    private String messenger;
    private String path;
}
