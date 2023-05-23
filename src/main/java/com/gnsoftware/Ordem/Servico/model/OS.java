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
@Table(name = "Ordem_Servico")
public class OS {
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

    @ManyToOne
    @JoinColumn(name = "servico_id")
    private Servico servico;

    @ManyToOne
    @JoinColumn(name = "produtoServico_id")
    private Produto produto;

    @Column(columnDefinition = "TEXT")
    private String observacoes;
    @ManyToOne
    @JoinColumn(name = "status_id")
    private StatusOrdemServico statusOrdemServico;

    private Double valorTotalOrdem = 0d;
}
