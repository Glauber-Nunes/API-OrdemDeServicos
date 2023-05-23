package com.gnsoftware.Ordem.Servico.services;

import com.gnsoftware.Ordem.Servico.dto.SituacaoOrdemForm;
import com.gnsoftware.Ordem.Servico.model.SituacaoOrdem;

public interface SituacaoOrdemService {

    SituacaoOrdem findById(Long ID);

    SituacaoOrdem update(Long id, SituacaoOrdemForm situacaoOrdemForm);
}
