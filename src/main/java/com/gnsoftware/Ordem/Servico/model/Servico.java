package com.gnsoftware.Ordem.Servico.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Servico {
    @Id
    private Long id;
    private String descricao;
    private Double preco;

}
