package com.gnsoftware.Ordem.Servico.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FornecedorForm {

    private Long id;
    @NotBlank(message = "NOME REQUERIDO")
    private String nome;
    private String municipio;
    private String uf;
    @CNPJ
    private String cnpj;
}
