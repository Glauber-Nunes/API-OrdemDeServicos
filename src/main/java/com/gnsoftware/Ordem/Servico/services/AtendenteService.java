package com.gnsoftware.Ordem.Servico.services;

import com.gnsoftware.Ordem.Servico.dto.AtendenteForm;
import com.gnsoftware.Ordem.Servico.dto.AtendenteForm;
import com.gnsoftware.Ordem.Servico.model.Atendente;
import com.gnsoftware.Ordem.Servico.model.Atendente;

import java.util.List;

public interface AtendenteService {
    Atendente save(AtendenteForm atendenteForm);

    Atendente update(Long id, AtendenteForm atendenteForm);

    Atendente findById(Long id);

    List<Atendente> findAll();

    void delete(Long id);
}
