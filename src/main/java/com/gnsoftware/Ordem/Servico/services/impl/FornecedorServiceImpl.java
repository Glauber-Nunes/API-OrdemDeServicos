package com.gnsoftware.Ordem.Servico.services.impl;

import com.gnsoftware.Ordem.Servico.dto.FornecedorForm;
import com.gnsoftware.Ordem.Servico.model.Fornecedor;
import com.gnsoftware.Ordem.Servico.repository.FornecedorRepository;
import com.gnsoftware.Ordem.Servico.services.FornecedorService;
import com.gnsoftware.Ordem.Servico.services.exceptions.DataIntegrityViolationException;
import com.gnsoftware.Ordem.Servico.services.exceptions.ModelNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorServiceImpl implements FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Override
    public Fornecedor save(FornecedorForm fornecedorForm) {

        this.existsByCnpj(fornecedorForm);

        return fornecedorRepository.save(
                Fornecedor.builder()
                        .id(null)
                        .nome(fornecedorForm.getNome())
                        .municipio(fornecedorForm.getMunicipio())
                        .uf(fornecedorForm.getUf())
                        .cnpj(fornecedorForm.getCnpj())
                        .build()
        );

    }

    @Override
    public Fornecedor update(Long id, FornecedorForm fornecedorForm) {

        Fornecedor fornecedorBanco = this.findById(id);

        return fornecedorRepository.saveAndFlush(
                Fornecedor.builder()
                        .id(fornecedorBanco.getId())
                        .nome(fornecedorForm.getNome() != null ? fornecedorForm.getNome() : fornecedorBanco.getNome())
                        .municipio(fornecedorForm.getMunicipio() != null ? fornecedorForm.getMunicipio() : fornecedorBanco.getMunicipio())
                        .uf(fornecedorForm.getUf() != null ? fornecedorForm.getUf() : fornecedorBanco.getUf())
                        .cnpj(fornecedorForm.getCnpj() != null ? fornecedorForm.getCnpj() : fornecedorBanco.getCnpj())
                        .build()

        );

    }

    @Override
    public void delete(Long id) {
        Fornecedor fornecedor = this.findById(id);

        fornecedorRepository.delete(fornecedor);
    }

    @Override
    public Fornecedor findById(Long id) {
        Optional<Fornecedor> fornecedor = fornecedorRepository.findById(id);

        return fornecedor.orElseThrow(() -> new ModelNotFound("Fornecedor Não Cadastrado"));
    }

    @Override
    public List<Fornecedor> findAll() {
        return fornecedorRepository.findAll();
    }

    private void existsByCnpj(FornecedorForm fornecedorForm) {
        if (fornecedorRepository.existsByCnpj(fornecedorForm.getCnpj())) {
            throw new DataIntegrityViolationException("CNPJ Ja Cadastrado na base de dados");
        }
    }
}
