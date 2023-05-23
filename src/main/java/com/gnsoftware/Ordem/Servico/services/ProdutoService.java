package com.gnsoftware.Ordem.Servico.services;

import com.gnsoftware.Ordem.Servico.dto.ProdutoForm;
import com.gnsoftware.Ordem.Servico.model.Produto;

public interface ProdutoService {

    Produto save(ProdutoForm produtoForm);
    Produto findById(Long id);
}
