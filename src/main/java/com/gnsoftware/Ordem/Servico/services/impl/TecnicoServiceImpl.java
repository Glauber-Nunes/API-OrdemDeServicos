package com.gnsoftware.Ordem.Servico.services.impl;

import com.gnsoftware.Ordem.Servico.dto.TecnicoForm;
import com.gnsoftware.Ordem.Servico.model.Tecnico;
import com.gnsoftware.Ordem.Servico.repository.TecnicoRepository;
import com.gnsoftware.Ordem.Servico.services.TecnicoService;
import com.gnsoftware.Ordem.Servico.services.exceptions.ModelNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TecnicoServiceImpl implements TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Override
    public Tecnico fidById(Long id) {
        Optional<Tecnico> tecnico = tecnicoRepository.findById(id);

        return tecnico.orElseThrow(() -> new ModelNotFound("Tecnico Não Encontrado"));
    }

    @Override
    public Tecnico save(TecnicoForm tecnicoForm) {
        return tecnicoRepository.save(
                Tecnico.builder()
                        .id(null)
                        .nome(tecnicoForm.getNome())
                        .build()
        );
    }
}
