package br.com.laticinioapi.controller;

import br.com.laticinioapi.dto.ProdutoDto;
import br.com.laticinioapi.entity.Produto;
import br.com.laticinioapi.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/produto")
@CrossOrigin("*")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Produto produto) {

        produtoService.save(produto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<Produto>> all() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(produtoService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody Produto produto, @PathVariable Long id) {
        produto.setId(id);
        produtoService.update(produto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/controle")
    public ResponseEntity<List<ProdutoDto>> allQuantidade() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(produtoService.getAllDto());
    }

    @GetMapping("/list")
    public ResponseEntity<List<Produto>> allListLimit() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(produtoService.findByListLimit());
    }

}
