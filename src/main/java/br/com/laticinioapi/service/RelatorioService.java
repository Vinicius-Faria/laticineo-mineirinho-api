package br.com.laticinioapi.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.laticinioapi.dto.RelatorioDto;
import br.com.laticinioapi.entity.Entrada;
import br.com.laticinioapi.entity.Saida;

@Service
public class RelatorioService {
	
	@Autowired
	private SaidaService saidaService;
	
	@Autowired
	private EntradaService entradaService;
	
	DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	public List<Saida> verificaRelatorio(RelatorioDto relatorio){
		if(relatorio.getNome().equals("Todos")) 
			return getListAllRelatorio(relatorio);
		else 
			return getListProdutoRelatorio(relatorio);
		
	}
	
	public List<Saida> getListAllRelatorio(RelatorioDto relatorio){
		
		var dataInicio = LocalDateTime.parse(relatorio.getDataInicio() + " 00:00:01", formato);
		var dataFim = LocalDateTime.parse(relatorio.getDataFim() + " 23:59:59", formato);
		
		return saidaService.findBySaidaBetweenData(dataInicio, dataFim);
	}
	
	public List<Saida> getListProdutoRelatorio(RelatorioDto relatorio){
		
		var dataInicio = LocalDateTime.parse(relatorio.getDataInicio() + " 00:00:01", formato);
		var dataFim = LocalDateTime.parse(relatorio.getDataFim() + " 23:59:59", formato);
		
		return saidaService.findBySaidaBetweenDataAndProduto(dataInicio, dataFim, relatorio.getNome());
	}
	
	
	public List<Entrada> verificaRelatorioEntrada(RelatorioDto relatorio){
		if(relatorio.getNome().equals("Todos")) 
			return getListAllRelatorioEntrada(relatorio);
		else 
			return getListProdutoRelatorioEntrada(relatorio);
		
	}
	
	public List<Entrada> getListAllRelatorioEntrada(RelatorioDto relatorio){
		
		var dataInicio = LocalDateTime.parse(relatorio.getDataInicio() + " 00:00:01", formato);
		var dataFim = LocalDateTime.parse(relatorio.getDataFim() + " 23:59:59", formato);
		
		return entradaService.findBySaidaBetweenData(dataInicio, dataFim);
	}
	
	public List<Entrada> getListProdutoRelatorioEntrada(RelatorioDto relatorio){
		
		var dataInicio = LocalDateTime.parse(relatorio.getDataInicio() + " 00:00:01", formato);
		var dataFim = LocalDateTime.parse(relatorio.getDataFim() + " 23:59:59", formato);
		
		return entradaService.findBySaidaBetweenDataAndProduto(dataInicio, dataFim, relatorio.getNome());
	}

}
