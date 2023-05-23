package com.gnsoftware.Ordem.Servico.controller;

import com.gnsoftware.Ordem.Servico.dto.OSForm;
import com.gnsoftware.Ordem.Servico.model.OS;
import com.gnsoftware.Ordem.Servico.services.OSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ordem_servicos")
public class OSController {

    @Autowired
    private OSService OSService;

    @GetMapping("/{id}")
    public ResponseEntity<OS> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(OSService.findById(id));
    }

    @PostMapping
    public ResponseEntity<OS> save(@RequestBody OSForm OSForm) {
        return ResponseEntity.status(HttpStatus.CREATED).body(OSService.save(OSForm));
    }
}
