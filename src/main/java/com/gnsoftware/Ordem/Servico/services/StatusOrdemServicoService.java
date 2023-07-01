package com.gnsoftware.Ordem.Servico.services;

import com.gnsoftware.Ordem.Servico.model.StatusOrdemServico;

import java.util.List;

public interface StatusOrdemServicoService {
    StatusOrdemServico findById(Long id);
    List<StatusOrdemServico> findAll();
    StatusOrdemServico update(Long id,StatusOrdemServico statusOrdemServico);
}
