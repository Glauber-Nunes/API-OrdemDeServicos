package com.gnsoftware.Ordem.Servico.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gnsoftware.Ordem.Servico.model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OSForm {

    private Long id;
    private Long atendente_id; //
    private Long situacaoOrdem_id;
    @NotEmpty(message = "CAMPO REQUERIDO")
    private Long cliente_id; // incluido
    private String descricao;
    private Long tecnico_id; // incluido
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm", locale = "pt-BR", timezone = "America/Sao_Paulo")
    private Date DataDoServico; // automaticamente
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm", locale = "pt-BR", timezone = "America/Sao_Paulo")
    private Date DataFechamento; // automaticamente no metodo finalizaServiço
    @NotEmpty(message = "CAMPO REQUERIDO")
    private Double quantidade;
    private Long servico_id; //
    private Long produto_id; //
    private Long fornecedor_id; //
    private String observacoes; //
    private Long statusOrdemServico_id; // automaticamente no POST
    private Double valorTotalOrdem = 0d;

    public Double calculaValorTotalOrdemServico(Servico servico, Produto produto) {

        this.valorTotalOrdem = (servico.getPreco() * this.quantidade) + produto.getPreco();

        return valorTotalOrdem;

    }

}
