package com.gnsoftware.Ordem.Servico.services.impl;

import com.gnsoftware.Ordem.Servico.dto.OrdemServicoForm;
import com.gnsoftware.Ordem.Servico.model.OrdemServico;
import com.gnsoftware.Ordem.Servico.repository.OrdemServicoRepository;
import com.gnsoftware.Ordem.Servico.services.OrdemServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class OrdemServicoServiceImpl implements OrdemServicoService {

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @Override
    public OrdemServico save(OrdemServicoForm ordemServicoForm) {
        return null;
    }

    @Override
    public OrdemServico update(Long id, OrdemServicoForm ordemServicoForm) {
        return null;
    }

    @Override
    public OrdemServico findById(OrdemServico ordemServico) {
        return null;
    }

    @Override
    public OrdemServico delete(Long id) {
        return null;
    }
}
