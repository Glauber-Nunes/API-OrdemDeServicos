package com.gnsoftware.Ordem.Servico.services;

import com.gnsoftware.Ordem.Servico.dto.ProdutoForm;
import com.gnsoftware.Ordem.Servico.model.Produto;

import java.util.List;

public interface ProdutoService {

    Produto save(ProdutoForm produtoForm);

    Produto update(Long id, ProdutoForm produtoForm);

    Produto findById(Long id);

    List<Produto> findAll();

    void delete(Long id);

    void fazerVenda(ProdutoForm produtoForm);
}
