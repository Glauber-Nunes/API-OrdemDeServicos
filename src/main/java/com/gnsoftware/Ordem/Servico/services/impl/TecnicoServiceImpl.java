package com.gnsoftware.Ordem.Servico.services.impl;

import com.gnsoftware.Ordem.Servico.dto.TecnicoForm;
import com.gnsoftware.Ordem.Servico.model.Tecnico;
import com.gnsoftware.Ordem.Servico.repository.TecnicoRepository;
import com.gnsoftware.Ordem.Servico.services.TecnicoService;
import com.gnsoftware.Ordem.Servico.services.exceptions.ModelNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TecnicoServiceImpl implements TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Override
    public Tecnico findById(Long id) {
        Optional<Tecnico> tecnico = tecnicoRepository.findById(id);

        return tecnico.orElseThrow(() -> new ModelNotFound("Tecnico Não Encontrado"));
    }

    @Override
    public List<Tecnico> findAll() {
        return tecnicoRepository.findAll();
    }

    @Override
    @Transactional
    public Tecnico save(TecnicoForm tecnicoForm) {

        return tecnicoRepository.save(
                Tecnico.builder()
                        .id(null)
                        .nome(tecnicoForm.getNome())
                        .build()
        );
    }

    @Override
    public Tecnico update(Long id, TecnicoForm tecnicoForm) {

        Tecnico tecnicoBanco = this.findById(id);

       Tecnico tecnico = Tecnico.builder()
                .id(tecnicoBanco.getId())
                .nome(tecnicoForm.getNome() != null ? tecnicoForm.getNome() : tecnicoBanco.getNome())
                .build();

        return tecnicoRepository.saveAndFlush(tecnico);

    }

    @Override
    public void delete(Long id) {
        Tecnico tecnico = this.findById(id);

        tecnicoRepository.delete(tecnico);
    }
}
