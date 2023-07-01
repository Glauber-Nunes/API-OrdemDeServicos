package com.gnsoftware.Ordem.Servico.services;

import com.gnsoftware.Ordem.Servico.dto.TecnicoForm;
import com.gnsoftware.Ordem.Servico.model.Tecnico;

import java.util.List;

public interface TecnicoService {

    Tecnico findById(Long id);

    Tecnico save(TecnicoForm tecnicoForm);

    Tecnico update(Long id, TecnicoForm tecnicoForm);

    void delete(Long id);

    List<Tecnico> findAll();
}
