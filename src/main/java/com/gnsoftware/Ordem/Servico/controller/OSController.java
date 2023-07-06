package com.gnsoftware.Ordem.Servico.controller;

import com.gnsoftware.Ordem.Servico.dto.OSForm;
import com.gnsoftware.Ordem.Servico.model.OS;
import com.gnsoftware.Ordem.Servico.services.OSService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
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

    @PutMapping("/{id}")
    public ResponseEntity<OS> update(@PathVariable Long id, @RequestBody OSForm osForm) {
        return ResponseEntity.status(HttpStatus.OK).body(OSService.update(id, osForm));
    }

    @GetMapping
    public ResponseEntity<List<OS>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(OSService.findAll());
    }

    @PutMapping("/finalizar-servico/{id}")
    public ResponseEntity<String> finalizaOs(@PathVariable Long id, @RequestBody OS os) {
        OSService.finalizaOs(id, os);

        JSONObject response = new JSONObject();
        response.put("message", "FINALIZADO COM SUCESSO");

        return ResponseEntity.status(HttpStatus.OK).body(response.toString());
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<String> delete(@PathVariable Long id) {
        OSService.delete(id);

        JSONObject response = new JSONObject();
        response.put("message", "EXCLUIDO COM SUCESSO");

        return ResponseEntity.status(HttpStatus.OK).body(response.toString());
    }
}
