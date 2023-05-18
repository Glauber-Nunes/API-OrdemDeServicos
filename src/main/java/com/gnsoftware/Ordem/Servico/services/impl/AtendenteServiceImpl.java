package com.gnsoftware.Ordem.Servico.services.impl;

import com.gnsoftware.Ordem.Servico.dto.AtendenteForm;
import com.gnsoftware.Ordem.Servico.model.Atendente;
import com.gnsoftware.Ordem.Servico.repository.AtendenteRepository;
import com.gnsoftware.Ordem.Servico.services.AtendenteService;
import com.gnsoftware.Ordem.Servico.services.exceptions.ModelNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AtendenteServiceImpl implements AtendenteService {

    @Autowired
    private AtendenteRepository atendenteRepository;

    @Override
    @Transactional
    public Atendente save(AtendenteForm atendenteForm) {

        return atendenteRepository.save(Atendente.builder()
                .id(null)
                .nome(atendenteForm.getNome())
                .cpf(atendenteForm.getCpf())
                .build());
    }

    @Override
    public Atendente update(Long id, AtendenteForm atendenteForm) {

        Atendente atendenteBanco = this.findById(id);

        return atendenteRepository.saveAndFlush(
                Atendente.builder()
                        .id(atendenteBanco.getId())
                        .nome(atendenteForm.getNome() != null ? atendenteForm.getNome() : atendenteBanco.getNome())
                        .cpf(atendenteForm.getCpf() != null ? atendenteForm.getCpf() : atendenteBanco.getCpf())
                        .build()
        );
    }

    @Override
    public Atendente findById(Long id) {
        Optional<Atendente> atendente = atendenteRepository.findById(id);

        if (atendente == null) throw new ModelNotFound("Atendente Não Encontrado");

        return atendente.get();
    }

    @Override
    public List<Atendente> findAll() {
        return null;
    }

    @Override
    public void delete(Long id) {
        Atendente atendente = this.findById(id);

        atendenteRepository.delete(atendente);
    }
}
