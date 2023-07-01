package com.gnsoftware.Ordem.Servico.controller;

import com.gnsoftware.Ordem.Servico.dto.SituacaoOrdemForm;
import com.gnsoftware.Ordem.Servico.model.SituacaoOrdem;
import com.gnsoftware.Ordem.Servico.services.SituacaoOrdemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/situacao-ordens")
public class SituacaoOrdemController {

    @Autowired
    private SituacaoOrdemService situacaoOrdemService;

    @PutMapping("/{id}")
    public ResponseEntity<SituacaoOrdem> update(@PathVariable Long id, @RequestBody SituacaoOrdemForm situacaoOrdemForm) {
        return ResponseEntity.status(HttpStatus.OK).body(situacaoOrdemService.update(id, situacaoOrdemForm));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SituacaoOrdem> findByid(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(situacaoOrdemService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<SituacaoOrdem>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(situacaoOrdemService.findAll());
    }
}
