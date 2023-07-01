package com.gnsoftware.Ordem.Servico.services;

import com.gnsoftware.Ordem.Servico.dto.SituacaoOrdemForm;
import com.gnsoftware.Ordem.Servico.model.SituacaoOrdem;

import java.util.List;
import java.util.UUID;

public interface SituacaoOrdemService {

    SituacaoOrdem findById(Long ID);
    List<SituacaoOrdem> findAll();
    SituacaoOrdem update(Long id, SituacaoOrdemForm situacaoOrdemForm);
}
