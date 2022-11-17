package br.com.laticinioapi.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.laticinioapi.entity.Entrada;
import br.com.laticinioapi.entity.Produto;
import br.com.laticinioapi.service.EntradaService;

@RestController
@RequestMapping(value = "/entrada")
@CrossOrigin("https://emporiomineirinho.vercel.app/")
public class EntradaController {
	
	@Autowired
	private EntradaService entradaService;
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody Produto produto) {

		var entrada = new Entrada();
		entrada.setProduto(produto.getNome());
		entrada.setQuantidade(produto.getQuantidade());
		entrada.setValor(produto.getPreco());
		entradaService.save(entrada);
		
		entradaService.alteraEstoque(produto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
							.path("/{id}").buildAndExpand(produto.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
}
