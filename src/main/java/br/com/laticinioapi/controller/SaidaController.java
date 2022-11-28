package br.com.laticinioapi.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.laticinioapi.entity.Saida;
import br.com.laticinioapi.service.SaidaService;

@RestController
@RequestMapping(value = "/saida")
@CrossOrigin("https://emporiomineirinho.vercel.app/")
//@CrossOrigin("*")
public class SaidaController {
	
	@Autowired
	private SaidaService saidaService;
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody List<Saida> saida) {

		for (Saida saidaNew : saida) {
			if(saidaService.alteraEstoque(saidaNew)) {
				saidaService.save(saidaNew);
			}
		}
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
		.path("/{id}").buildAndExpand(saida.get(0).getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("/numero")
	public String verificaLogin() {
		return saidaService.findByNumeroVenda();
	}

}
