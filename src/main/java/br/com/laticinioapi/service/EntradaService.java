package br.com.laticinioapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.laticinioapi.entity.Entrada;
import br.com.laticinioapi.entity.Produto;
import br.com.laticinioapi.repository.EntradaRepository;

@Service
public class EntradaService {

	@Autowired
	private EntradaRepository entradaRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	public Entrada save(Entrada entrada) {
		return entradaRepository.save(entrada);
	}
	
	public void alteraEstoque(Produto produto) {
		
		var upProduto = produtoService.findById(produto.getId());
		
		var total = upProduto.get().getQuantidade() + produto.getQuantidade();
		produto.setQuantidade(total);	
		
		BeanUtils.copyProperties(produto, upProduto.get());
		produtoService.save(upProduto.get());
		
	}
	
}
