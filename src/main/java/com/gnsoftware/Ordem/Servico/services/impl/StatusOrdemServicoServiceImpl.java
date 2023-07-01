package com.gnsoftware.Ordem.Servico.services.impl;

import com.gnsoftware.Ordem.Servico.model.StatusOrdemServico;
import com.gnsoftware.Ordem.Servico.repository.StatusOrdemServicoRepository;
import com.gnsoftware.Ordem.Servico.services.StatusOrdemServicoService;
import com.gnsoftware.Ordem.Servico.services.exceptions.ModelNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class StatusOrdemServicoServiceImpl implements StatusOrdemServicoService {

    @Autowired
    private StatusOrdemServicoRepository statusOrdemServicoRepository;

    @Override
    public StatusOrdemServico findById(Long id) {
        Optional<StatusOrdemServico> statusOrdemServico = statusOrdemServicoRepository.findById(id);

        return statusOrdemServico.orElseThrow(() -> new ModelNotFound("Not Found"));
    }

    @Override
    public List<StatusOrdemServico> findAll() {
        return statusOrdemServicoRepository.findAll();
    }

    @Override
    public StatusOrdemServico update(Long id, StatusOrdemServico statusOrdemServico) {

        StatusOrdemServico statusBanco = this.findById(id);

        StatusOrdemServico statusOrdemServico1 = StatusOrdemServico.builder()
                .id(statusBanco.getId())
                .nome(statusOrdemServico.getNome())
                .build();

        return statusOrdemServicoRepository.save(statusOrdemServico1);
    }
}
