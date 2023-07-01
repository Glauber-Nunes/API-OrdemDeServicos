package com.gnsoftware.Ordem.Servico.services;

import com.gnsoftware.Ordem.Servico.dto.FornecedorForm;
import com.gnsoftware.Ordem.Servico.model.Fornecedor;

import java.util.List;

public interface FornecedorService {

    Fornecedor save(FornecedorForm fornecedorForm);

    Fornecedor update(Long id, FornecedorForm fornecedorForm);

    void delete(Long id);

    Fornecedor findById(Long id);

   List<Fornecedor> findAll();
}
