package com.gnsoftware.Ordem.Servico.services;

import com.gnsoftware.Ordem.Servico.dto.OrdemServicoForm;
import com.gnsoftware.Ordem.Servico.model.OrdemServico;

public interface OrdemServicoService {

    OrdemServico save(OrdemServicoForm ordemServicoForm);

    OrdemServico update(Long id, OrdemServicoForm ordemServicoForm);

    OrdemServico findById(OrdemServico ordemServico);

    OrdemServico delete(Long id);
}
