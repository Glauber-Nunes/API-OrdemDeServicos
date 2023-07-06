package com.gnsoftware.Ordem.Servico.services;

import com.gnsoftware.Ordem.Servico.dto.OSForm;
import com.gnsoftware.Ordem.Servico.model.OS;

import java.util.List;

public interface OSService {

    OS save(OSForm OSForm);

    OS update(Long id, OSForm OSForm);

    OS findById(Long id);

    void delete(Long id);

    List<OS> findAll();
    void finalizaOs (Long id,OS os);
}
