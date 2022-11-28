package br.com.laticinioapi.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.laticinioapi.entity.Produto;
import br.com.laticinioapi.entity.Saida;
import br.com.laticinioapi.repository.SaidaRepository;

@Service
public class SaidaService {
	
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

}
