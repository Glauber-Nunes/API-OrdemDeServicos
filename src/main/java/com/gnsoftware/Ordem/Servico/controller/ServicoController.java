package com.gnsoftware.Ordem.Servico.controller;

import com.gnsoftware.Ordem.Servico.dto.ServicoForm;
import com.gnsoftware.Ordem.Servico.model.Servico;
import com.gnsoftware.Ordem.Servico.services.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/servicos")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    @PostMapping
    public ResponseEntity<Servico> save(@Valid @RequestBody ServicoForm servicoForm) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicoService.save(servicoForm));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servico> update(@PathVariable Long id, @RequestBody ServicoForm servicoForm) {
        return ResponseEntity.status(HttpStatus.OK).body(servicoService.update(id, servicoForm));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servico> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(servicoService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Servico>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(servicoService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        servicoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Servico Deletado");
    }

}
