package br.com.laticinioapi.service;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.laticinioapi.dto.GraficoEntradaSaidaDto;
import br.com.laticinioapi.entity.Entrada;
import br.com.laticinioapi.entity.Produto;
import br.com.laticinioapi.repository.EntradaRepository;

@Service
public class EntradaService {
	
	DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	@Autowired
	private EntradaRepository entradaRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	public Entrada save(Entrada entrada) {
		return entradaRepository.save(entrada);
	}
	
	public void alteraEstoque(Produto produto) {
		
		DecimalFormat df = new DecimalFormat("0.00");
		df.setRoundingMode(RoundingMode.HALF_UP);
		
		var upProduto = produtoService.findById(produto.getId());
		
		var total = Double.valueOf(upProduto.get().getQuantidade().replace(',', '.')) + Double.valueOf(produto.getQuantidade());
		produto.setQuantidade(String.valueOf(df.format(Double.valueOf(total))));
		
		BeanUtils.copyProperties(produto, upProduto.get());
		produtoService.save(upProduto.get());
		
	}
	
	public List<Entrada> findBySaidaBetweenData(LocalDateTime dataInicio, LocalDateTime dataFim){
		return entradaRepository.findByDataBetween(dataInicio, dataFim);
	}
	
	public List<Entrada> findBySaidaBetweenDataAndProduto(LocalDateTime dataInicio, LocalDateTime dataFim, String nome){
		return entradaRepository.findByDataBetweenAndNome(dataInicio, dataFim, nome);	
	}
	
	public GraficoEntradaSaidaDto findDataQuantidadeEntrada() {
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
			
			var listQuantidade = entradaRepository.findQuantidadeByData(dataInicio, dataFim);
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
