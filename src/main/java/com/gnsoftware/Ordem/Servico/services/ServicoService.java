package com.gnsoftware.Ordem.Servico.services;

import com.gnsoftware.Ordem.Servico.dto.ServicoForm;
import com.gnsoftware.Ordem.Servico.model.Servico;

public interface ServicoService {

    Servico save(ServicoForm servicoForm);

    Servico findById(Long id);
}
