package com.gnsoftware.Ordem.Servico.services;

import com.gnsoftware.Ordem.Servico.dto.TecnicoForm;
import com.gnsoftware.Ordem.Servico.model.Tecnico;

public interface TecnicoService {

    Tecnico fidById(Long id);

    Tecnico save(TecnicoForm tecnicoForm);
}
