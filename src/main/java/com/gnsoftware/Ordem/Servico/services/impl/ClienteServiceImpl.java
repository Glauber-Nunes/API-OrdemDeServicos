package com.gnsoftware.Ordem.Servico.services.impl;

import com.gnsoftware.Ordem.Servico.dto.ClienteForm;
import com.gnsoftware.Ordem.Servico.model.Cliente;
import com.gnsoftware.Ordem.Servico.repository.ClienteRepository;
import com.gnsoftware.Ordem.Servico.services.ClienteService;
import com.gnsoftware.Ordem.Servico.services.exceptions.ModelNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    @Transactional
    public Cliente save(ClienteForm clienteForm) {

        return clienteRepository.save(
                Cliente.builder()
                        .id(null)
                        .nome(clienteForm.getNome())
                        .cpf(clienteForm.getCpf())
                        .rg(clienteForm.getRg())
                        .telefone(clienteForm.getTelefone())
                        .email(clienteForm.getEmail())
                        .cidade(clienteForm.getCidade())
                        .build()
        );

    }

    @Override
    @Transactional
    public Cliente update(Long id, ClienteForm clienteForm) {

        Cliente clienteBanco = this.findById(id);

        return clienteRepository.saveAndFlush(
                Cliente.builder()
                        .id(clienteBanco.getId())
                        .nome(clienteForm.getNome() != null ? clienteForm.getNome() : clienteBanco.getNome())
                        .cpf(clienteForm.getCpf() != null ? clienteForm.getCpf() : clienteBanco.getCpf())
                        .rg(clienteForm.getRg() != null ? clienteForm.getRg() : clienteBanco.getRg())
                        .telefone(clienteForm.getTelefone() != null ? clienteForm.getTelefone() : clienteBanco.getTelefone())
                        .email(clienteForm.getEmail() != null ? clienteForm.getEmail() : clienteBanco.getEmail())
                        .cidade(clienteForm.getCidade() != null ? clienteForm.getCidade() : clienteBanco.getCidade())
                        .build()
        );

    }

    @Override
    public Cliente findById(Long id) {

        Optional<Cliente> cliente = clienteRepository.findById(id);

        if (cliente == null) throw new ModelNotFound("CLIENTE NÃO ENCONTRADO");

        return cliente.get();

    }

    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        Cliente cliente = this.findById(id);

        clienteRepository.delete(cliente);
    }
}
