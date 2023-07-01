package com.gnsoftware.Ordem.Servico.services.impl;

import com.gnsoftware.Ordem.Servico.dto.ProdutoForm;
import com.gnsoftware.Ordem.Servico.model.Produto;
import com.gnsoftware.Ordem.Servico.repository.ProdutoRepository;
import com.gnsoftware.Ordem.Servico.services.ProdutoService;
import com.gnsoftware.Ordem.Servico.services.exceptions.ModelNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PrudutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    @Transactional
    public Produto save(ProdutoForm produtoForm) {

        return produtoRepository.save(
                Produto.builder()
                        .id(null)
                        .descricao(produtoForm.getDescricao())
                        .preco(produtoForm.getPreco())
                        .codeBarras(produtoForm.getCodeBarras())
                        .unEntrada(produtoForm.getUnEntrada())
                        .unSaida(produtoForm.getUnSaida())
                        .estoque(produtoForm.getEstoque())
                        .build()
        );

    }

    @Override
    @Transactional
    public Produto update(Long id, ProdutoForm produtoForm) {

        Produto produtoBanco = this.findById(id);

        Produto produto = Produto.builder()
                .id(produtoBanco.getId())
                .descricao(produtoForm.getDescricao() != null ? produtoForm.getDescricao() : produtoBanco.getDescricao())
                .preco(produtoForm.getPreco() != null ? produtoForm.getPreco() : produtoBanco.getPreco())
                .codeBarras(produtoForm.getCodeBarras() != null ? produtoForm.getCodeBarras() : produtoBanco.getCodeBarras())
                .unEntrada(produtoForm.getUnEntrada() != null ? produtoForm.getUnEntrada() : produtoBanco.getUnEntrada())
                .unSaida(produtoForm.getUnSaida() != null ? produtoForm.getUnSaida() : produtoBanco.getUnSaida())
                .estoque(produtoForm.getEstoque() != null ? produtoForm.getEstoque() : produtoBanco.getEstoque())
                .build();

        return produtoRepository.saveAndFlush(produto);
    }

    @Override
    public Produto findById(Long id) {

        Optional<Produto> produto = produtoRepository.findById(id);

        return produto.orElseThrow(() -> new ModelNotFound("Produto Não Foi Encontrado"));

    }

    @Override
    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        Produto produto = this.findById(id);

        produtoRepository.delete(produto);
    }

    @Override
    public void fazerVenda(ProdutoForm produtoForm){
        Produto produtoBanco = this.findById(produtoForm.getId());
    }
}
