package com.gnsoftware.Ordem.Servico.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteForm {

    private String nome;
    private String cpf;
    private String rg;
    private String telefone;
    private String email;
    private String cidade;
}
