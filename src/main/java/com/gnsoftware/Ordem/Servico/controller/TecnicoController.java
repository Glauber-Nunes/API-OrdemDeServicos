package com.gnsoftware.Ordem.Servico.controller;

import com.gnsoftware.Ordem.Servico.dto.TecnicoForm;
import com.gnsoftware.Ordem.Servico.model.Tecnico;
import com.gnsoftware.Ordem.Servico.services.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/tecnicos")
public class TecnicoController {

    @Autowired
    private TecnicoService tecnicoService;

    @PostMapping
    public ResponseEntity<Tecnico> save(@Valid @RequestBody TecnicoForm tecnicoForm) {
        return ResponseEntity.status(HttpStatus.OK).body(tecnicoService.save(tecnicoForm));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tecnico> update(@PathVariable Long id, @RequestBody TecnicoForm tecnicoForm) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tecnicoService.update(id,tecnicoForm));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tecnico> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(tecnicoService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Tecnico>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(tecnicoService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        tecnicoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Tecnico Deletado!");
    }
}
