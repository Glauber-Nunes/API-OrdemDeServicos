package com.gnsoftware.Ordem.Servico.controller;

import com.gnsoftware.Ordem.Servico.dto.FornecedorForm;
import com.gnsoftware.Ordem.Servico.dto.TransportadoraForm;
import com.gnsoftware.Ordem.Servico.model.Fornecedor;
import com.gnsoftware.Ordem.Servico.services.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/fornecedores")
public class FornecedoraController {
    @Autowired
    private FornecedorService fornecedorService;

    @PostMapping
    public ResponseEntity<Fornecedor> save(@Valid @RequestBody FornecedorForm fornecedorForm) {
        return ResponseEntity.status(HttpStatus.CREATED).body(fornecedorService.save(fornecedorForm));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fornecedor> update(@PathVariable Long id, @RequestBody FornecedorForm fornecedorForm) {
        return ResponseEntity.status(HttpStatus.OK).body(fornecedorService.update(id, fornecedorForm));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        fornecedorService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Atendente Deletado");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fornecedor> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(fornecedorService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Fornecedor>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(fornecedorService.findAll());
    }

}
