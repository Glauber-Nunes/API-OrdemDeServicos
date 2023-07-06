package com.gnsoftware.Ordem.Servico.services.impl;

import com.gnsoftware.Ordem.Servico.dto.AtendenteForm;
import com.gnsoftware.Ordem.Servico.model.Atendente;
import com.gnsoftware.Ordem.Servico.repository.AtendenteRepository;
import com.gnsoftware.Ordem.Servico.services.AtendenteService;
import com.gnsoftware.Ordem.Servico.services.exceptions.DataIntegrityViolationException;
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

        this.existsByCpf(atendenteForm);
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

        return atendente.orElseThrow(() -> new ModelNotFound("Atendente Não Encontrado"));
    }

    @Override
    public List<Atendente> findAll() {
        return atendenteRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        Atendente atendente = this.findById(id);

        atendenteRepository.delete(atendente);
    }

    private void existsByCpf(AtendenteForm atendente) {
        if (atendenteRepository.existsByCpf(atendente.getCpf())) {
            throw new DataIntegrityViolationException("CPF Ja Cadastrado na base de dados");
        }
    }
}
