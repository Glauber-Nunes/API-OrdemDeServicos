package com.gnsoftware.Ordem.Servico.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cliente {
    @Id
    private Long id;
    private String nome;
    private String cpf;
    private String RG_IE;
    private String telefone;
    private String email;
    private String cidade;

}
