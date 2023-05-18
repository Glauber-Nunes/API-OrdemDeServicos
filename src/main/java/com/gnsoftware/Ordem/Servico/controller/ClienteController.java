package com.gnsoftware.Ordem.Servico.controller;

import com.gnsoftware.Ordem.Servico.dto.AtendenteForm;
import com.gnsoftware.Ordem.Servico.dto.ClienteForm;
import com.gnsoftware.Ordem.Servico.model.Cliente;
import com.gnsoftware.Ordem.Servico.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> save(@RequestBody ClienteForm clienteForm) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(clienteForm));
    }
}
