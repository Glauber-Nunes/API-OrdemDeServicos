package com.gnsoftware.Ordem.Servico.controller;

import com.gnsoftware.Ordem.Servico.dto.ProdutoForm;
import com.gnsoftware.Ordem.Servico.model.Produto;
import com.gnsoftware.Ordem.Servico.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<Produto> save(@Valid @RequestBody ProdutoForm produtoForm) {
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.save(produtoForm));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> update(@PathVariable Long id, @RequestBody ProdutoForm produtoForm) {
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.update(id, produtoForm));
    }

    @GetMapping
    public ResponseEntity<List<Produto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        produtoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Produto Deletado");
    }

    @PostMapping("/fazer-venda")
    public ResponseEntity<String> fazerVenda (@RequestBody ProdutoForm produtoForm){
        produtoService.fazerVenda(produtoForm);
        return ResponseEntity.status(HttpStatus.CREATED).body("VENDA FEITA COM SUCESSO");
    }

}
