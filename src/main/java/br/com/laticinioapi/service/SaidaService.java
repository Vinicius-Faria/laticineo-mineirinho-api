package br.com.laticinioapi.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.laticinioapi.dto.GraficoEntradaSaidaDto;
import br.com.laticinioapi.entity.Produto;
import br.com.laticinioapi.entity.Saida;
import br.com.laticinioapi.repository.SaidaRepository;

@Service
public class SaidaService {
	
	DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	private SaidaRepository saidaRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	public Saida save(Saida saida) {
		saida.setData(LocalDateTime.now());
		return saidaRepository.save(saida);
	}
	
	public List<Saida> getAll(){
		return saidaRepository.findAll();
	}
	
	public Optional<Saida> findById(Long id){
		return saidaRepository.findById(id);
	}
	
	public String findByNumeroVenda() {
		return saidaRepository.findByVendaLimit1().getVenda();
	}
	
	public List<Saida> findBySaidaBetweenData(LocalDateTime dataInicio, LocalDateTime dataFim){
		return saidaRepository.findByDataBetween(dataInicio, dataFim);
	}
	
	public List<Saida> findBySaidaBetweenDataAndProduto(LocalDateTime dataInicio, LocalDateTime dataFim, String nome){
		return saidaRepository.findByDataBetweenAndNome(dataInicio, dataFim, nome);	
	}
	
	public boolean alteraEstoque(Saida saida) {
		
		var ListProduto = produtoService.getAll();
		
		for (Produto produto : ListProduto) {
			if(saida.getNome().equals(produto.getNome())) {
				if(Double.valueOf(produto.getQuantidade()) > Double.valueOf(saida.getQuantidade())) {
					var upProduto = produto;
					var total = String.valueOf(Double.valueOf(upProduto.getQuantidade()) - Double.valueOf(saida.getQuantidade()));
					
					upProduto.setQuantidade(total);	
					produtoService.save(upProduto);
					return true;
				}else {
					
				}
			}
		}
		
		return false;
	}
	
	public GraficoEntradaSaidaDto findDataQuantidadeSaida() {
		return returnQuantidade(returnData());
	}

	public GraficoEntradaSaidaDto returnQuantidade(List<String> listData){
		
		var listQuantidadeTotal = new ArrayList<Double>();
		var listDataTotal = new ArrayList<String>();
		var grafico = new GraficoEntradaSaidaDto();
		
		for(int i = 0; i< listData.size(); i++) {
			var dataInicio = LocalDateTime.parse(listData.get(i).substring(0, 10) + " 00:00:01", formato);
			var dataFim = LocalDateTime.parse(listData.get(i).substring(0, 10) + " 23:59:59", formato);
			listDataTotal.add(String.valueOf(dataInicio).substring(0, 10));
			
			var listQuantidade = saidaRepository.findQuantidadeByData(dataInicio, dataFim);
			double total = 0;
			for (String string : listQuantidade) {
				total = total + Double.valueOf(string);
			}
			
			listQuantidadeTotal.add(total);
			
		}
		
		grafico.setQuantidade(listQuantidadeTotal);
		grafico.setData(listDataTotal);
		
		return grafico;
	}
	
	public List<String> returnData(){
		var listData = new ArrayList<String>();
		var dataAtual = LocalDateTime.now();
		listData.add(String.valueOf(dataAtual));
		for(int i = 1; i < 7; i++) {
			listData.add(String.valueOf(dataAtual.minusDays(i)));
		}
		
		return listData;
	}

}
