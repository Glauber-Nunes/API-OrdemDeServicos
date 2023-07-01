package com.gnsoftware.Ordem.Servico.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AtendenteForm {
    private Long id;
    @NotBlank(message = "NOME REQUERIDO")
    private String nome;
    @CPF
    @Column(unique = true)
    private String cpf;

}
