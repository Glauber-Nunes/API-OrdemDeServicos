package com.gnsoftware.Ordem.Servico.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServicoForm {

    private Long id;
    @NotBlank(message = "DESCRIÇÃO REQUERIDO")
    private String descricao;
    private Double preco;
}
