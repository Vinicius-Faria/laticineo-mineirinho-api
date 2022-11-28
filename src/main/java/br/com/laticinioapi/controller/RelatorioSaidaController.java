package br.com.laticinioapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.laticinioapi.dto.RelatorioDto;
import br.com.laticinioapi.entity.ResourceDto;
import br.com.laticinioapi.service.RelatorioSaidaService;
import br.com.laticinioapi.service.RelatorioService;

@RestController
@RequestMapping(value = "/relatorio")
@CrossOrigin("https://emporiomineirinho.vercel.app/")
//@CrossOrigin("*")
public class RelatorioSaidaController {
	
	@Autowired
	private RelatorioService relatorioService;
	
	@Autowired
	private RelatorioSaidaService relatorioSaidaSerive;
	
	@GetMapping("/saida")
	public ResponseEntity<byte[]> byFornecedorOrAll(@RequestParam(required = false) String nome,
			@RequestParam(required = false) String dataInicio,
			@RequestParam(required = false) String dataFim) throws Exception{
		ResourceDto dto = null;
		
		var relatorioDto = new RelatorioDto();
		relatorioDto.setNome(nome);
		relatorioDto.setDataInicio(dataInicio.substring(1, 11));
		relatorioDto.setDataFim(dataFim.substring(1, 11));
		
		var list = relatorioService.verificaRelatorio(relatorioDto);
		
		dto = relatorioSaidaSerive.gera(list);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentLength(dto.getBytes().length);
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + dto.getFileName());
		return new ResponseEntity<byte[]>(dto.getBytes(), headers, HttpStatus.OK);
	}
}
