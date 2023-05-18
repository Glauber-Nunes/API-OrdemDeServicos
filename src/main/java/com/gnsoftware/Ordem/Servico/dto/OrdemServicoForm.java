package com.gnsoftware.Ordem.Servico.dto;

import com.gnsoftware.Ordem.Servico.model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdemServicoForm {

    private Long id;
    private Integer atendente_id;
    private Integer situacaoOrdem_id;
    private Integer cliente_id;
    private String descricao;
    private Integer tecnico_id;
    private Instant DataDoServico = Instant.now();
    private Double quantidade;
    private Double valorUnitario;
    private Integer produto_id;
    private Integer servico_id;
    private String Observacoes;
    private Double valorTotalOrdem = 0.0;
}
