package com.gnsoftware.Ordem.Servico.controller;

import com.gnsoftware.Ordem.Servico.dto.AtendenteForm;
import com.gnsoftware.Ordem.Servico.model.Atendente;
import com.gnsoftware.Ordem.Servico.services.AtendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atendentes")
public class AtendenteController {

    @Autowired
    private AtendenteService atendenteService;

    @PostMapping
    public ResponseEntity<Atendente> save(@RequestBody AtendenteForm atendenteForm) {
        return ResponseEntity.status(HttpStatus.CREATED).body(atendenteService.save(atendenteForm));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Atendente> update(@PathVariable Long id, @RequestBody AtendenteForm atendenteForm) {
        return ResponseEntity.status(HttpStatus.OK).body(atendenteService.update(id, atendenteForm));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Atendente> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(atendenteService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Atendente>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(atendenteService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        atendenteService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Atendente Deletado!");
    }
}
