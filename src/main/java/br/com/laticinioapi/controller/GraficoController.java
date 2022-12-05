package br.com.laticinioapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.laticinioapi.dto.GraficoEntradaSaidaDto;
import br.com.laticinioapi.service.EntradaService;
import br.com.laticinioapi.service.SaidaService;

@RestController
@RequestMapping(value = "/grafico")
//@CrossOrigin("https://emporiomineirinho.vercel.app/")
@CrossOrigin("*")
public class GraficoController {
	
	@Autowired
	private SaidaService saidaService;
	
	@Autowired
	private EntradaService entradaService;
	
	@GetMapping("/saida")
	public GraficoEntradaSaidaDto bySaida() {
		return saidaService.findDataQuantidadeSaida();
	}
	
	@GetMapping("/entrada")
	public GraficoEntradaSaidaDto byEntrada() {
		return entradaService.findDataQuantidadeEntrada();
	}
	
	@GetMapping("/valor")
	public GraficoEntradaSaidaDto byValor() {
		return saidaService.findValorDataSaida();
	}

}
