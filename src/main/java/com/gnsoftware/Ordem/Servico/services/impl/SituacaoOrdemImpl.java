package com.gnsoftware.Ordem.Servico.services.impl;

import com.gnsoftware.Ordem.Servico.dto.SituacaoOrdemForm;
import com.gnsoftware.Ordem.Servico.model.SituacaoOrdem;
import com.gnsoftware.Ordem.Servico.repository.SituacaoOrdemRepository;
import com.gnsoftware.Ordem.Servico.services.SituacaoOrdemService;
import com.gnsoftware.Ordem.Servico.services.exceptions.ModelNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SituacaoOrdemImpl implements SituacaoOrdemService {

    @Autowired
    private SituacaoOrdemRepository situacaoOrdemRepository;

    @Override
    public SituacaoOrdem findById(Long ID) {
        Optional<SituacaoOrdem> situacaoOrdem = situacaoOrdemRepository.findById(ID);

        return situacaoOrdem.orElseThrow(() -> new ModelNotFound("Not Found"));
    }

    @Override
    public SituacaoOrdem update(Long id, SituacaoOrdemForm situacaoOrdemForm) {

        SituacaoOrdem situacaoOrdemBanco = this.findById(id);

        return situacaoOrdemRepository.saveAndFlush(
                SituacaoOrdem.builder()
                        .id(situacaoOrdemBanco.getId())
                        .nome(situacaoOrdemForm.getNome() != null ? situacaoOrdemForm.getNome() : situacaoOrdemBanco.getNome())
                        .build()
        );

    }
}
