package com.gnsoftware.Ordem.Servico.services.impl;

import com.gnsoftware.Ordem.Servico.dto.ProdutoForm;
import com.gnsoftware.Ordem.Servico.model.Produto;
import com.gnsoftware.Ordem.Servico.repository.ProdutoRepository;
import com.gnsoftware.Ordem.Servico.services.ProdutoService;
import com.gnsoftware.Ordem.Servico.services.exceptions.ModelNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PrudutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public Produto save(ProdutoForm produtoForm) {

        return produtoRepository.save(
                Produto.builder()
                        .id(null)
                        .descricao(produtoForm.getDescricao())
                        .preco(produtoForm.getPreco())
                        .build()
        );

    }

    @Override
    public Produto findById(Long id) {

        Optional<Produto> produto = produtoRepository.findById(id);

        return produto.orElseThrow(() -> new ModelNotFound("Produto Não Foi Encontrado"));

    }
}
