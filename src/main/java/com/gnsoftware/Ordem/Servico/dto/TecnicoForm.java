package com.gnsoftware.Ordem.Servico.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TecnicoForm {
    private Long id;
    @NotBlank(message = "NOME REQUERIDO")
    private String nome;
}
