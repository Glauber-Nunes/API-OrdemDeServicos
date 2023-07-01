package com.gnsoftware.Ordem.Servico.services.impl;

import com.gnsoftware.Ordem.Servico.dto.ProdutoForm;
import com.gnsoftware.Ordem.Servico.dto.ServicoForm;
import com.gnsoftware.Ordem.Servico.model.Produto;
import com.gnsoftware.Ordem.Servico.model.Servico;
import com.gnsoftware.Ordem.Servico.repository.ProdutoRepository;
import com.gnsoftware.Ordem.Servico.repository.ServicoRepository;
import com.gnsoftware.Ordem.Servico.services.ProdutoService;
import com.gnsoftware.Ordem.Servico.services.ServicoService;
import com.gnsoftware.Ordem.Servico.services.exceptions.ModelNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ServicoServiceImpl implements ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    @Override
    public Servico save(ServicoForm servicoForm) {

        return servicoRepository.save(
                Servico.builder()
                        .id(null)
                        .descricao(servicoForm.getDescricao())
                        .preco(servicoForm.getPreco())
                        .build()
        );

    }

    @Override
    @Transactional
    public Servico update(Long id, ServicoForm servicoForm) {
        Servico servicoBanco = this.findById(id);

        Servico servico = Servico.builder()
                .id(servicoBanco.getId())
                .descricao(servicoForm.getDescricao() != null ? servicoForm.getDescricao() : servicoBanco.getDescricao())
                .preco(servicoForm.getPreco() != null ? servicoForm.getPreco() : servicoBanco.getPreco())
                .build();

        return servicoRepository.saveAndFlush(servico);
    }

    @Override
    public Servico findById(Long id) {

        Optional<Servico> servico = servicoRepository.findById(id);

        return servico.orElseThrow(() -> new ModelNotFound("Servico Não Foi Encontrado"));

    }

    @Override
    public List<Servico> findAll() {
        return servicoRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Servico servico = this.findById(id);

        servicoRepository.delete(servico);
    }
}
