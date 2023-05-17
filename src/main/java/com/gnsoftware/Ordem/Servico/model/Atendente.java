package com.gnsoftware.Ordem.Servico.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Atendente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    @OneToMany(mappedBy = "atendente")
    @JsonIgnore
    private List<OrdemServico> ordemServicos = new ArrayList<>();

}
