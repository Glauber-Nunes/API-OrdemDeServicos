package com.gnsoftware.Ordem.Servico.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdemServico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "atendente_id")
    private Atendente atendente;
    @ManyToOne
    @JoinColumn(name = "situacaoOrdem_id")
    private SituacaoOrdem situacaoOrdem;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    private String descricao;
    @ManyToOne
    @JoinColumn(name = "tecnico_id")
    private Tecnico tecnico;
    private Instant DataDoServico = Instant.now();
    private Double quantidade;
    private Double valorUnitario;
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;
    @ManyToOne
    @JoinColumn(name = "servico_id")
    private Servico servico;
    @Column(columnDefinition = "TEXT")
    private String Observacoes;
    private Double valorTotalOrdem = 0d;
    @ManyToOne
    @JoinColumn(name = "status_id")
    private StatusOrdemServico statusOrdemServico;

    private Double calculaValorTotalOrdem() {

        valorTotalOrdem = (valorUnitario * quantidade);

        return valorTotalOrdem;
    }
}
