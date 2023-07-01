package com.gnsoftware.Ordem.Servico.services;

import com.gnsoftware.Ordem.Servico.dto.ServicoForm;
import com.gnsoftware.Ordem.Servico.model.Servico;

import java.util.List;

public interface ServicoService {

    Servico save(ServicoForm servicoForm);

    Servico update(Long id, ServicoForm servicoForm);

    Servico findById(Long id);

    List<Servico> findAll();

    void delete(Long id);
}