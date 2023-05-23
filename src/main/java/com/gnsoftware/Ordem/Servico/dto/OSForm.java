package com.gnsoftware.Ordem.Servico.dto;

import com.gnsoftware.Ordem.Servico.model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OSForm {

    private Long id;
    private Long atendente_id;
    private Long situacaoOrdem_id;
    private Long cliente_id;
    private String descricao;
    private Long tecnico_id;
    private Instant DataDoServico;
    private Double quantidade;
    private Long servico_id;
    private Long produto_id;
    private String observacoes;
    private Long statusOrdemServico_id;
    private Double valorTotalOrdem = 0d;

    public Double calculaValorTotalOrdemServico(Servico servico, Produto produto) {

        valorTotalOrdem = (servico.getPreco() * quantidade) + produto.getPreco();

        return valorTotalOrdem;

    }

}
