package com.gnsoftware.Ordem.Servico.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteForm {

    private Long id;
    @NotEmpty(message = "NOME REQUERIDO")
    private String nome;
    @CPF
    private String cpf;
    private String rg;
    private String telefone;
    private String email;
    private String endereco;
}
