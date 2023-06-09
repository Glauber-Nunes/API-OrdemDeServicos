package com.gnsoftware.Ordem.Servico.services;

import com.gnsoftware.Ordem.Servico.dto.ClienteForm;
import com.gnsoftware.Ordem.Servico.model.Cliente;
import com.gnsoftware.Ordem.Servico.services.exceptions.DataIntegrityViolationException;

import java.util.List;

public interface ClienteService {
    Cliente save(ClienteForm clienteForm, DataIntegrityViolationException d);

    Cliente update(Long id, ClienteForm clienteForm);

    Cliente findById(Long id);

    List<Cliente> findAll();

    void delete(Long id);
}
