package br.com.laticinioapi.service;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.laticinioapi.entity.Produto;
import br.com.laticinioapi.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Produto save(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	public List<Produto> getAll(){
		return produtoRepository.findAll();
	}
	
	public Optional<Produto> findById(Long id){
		return produtoRepository.findById(id);
	}
	
	public void update(Produto produto) {
		
		DecimalFormat df = new DecimalFormat("0.00");
		df.setRoundingMode(RoundingMode.HALF_UP);
		
		var upProduto = findById(produto.getId());
		produto.setQuantidade(String.valueOf(Double.valueOf(df.format(upProduto.get().getQuantidade().replace(",", ".")))));	
		produto.setCodigo(upProduto.get().getCodigo());
		BeanUtils.copyProperties(produto, upProduto.get());
		produtoRepository.save(upProduto.get());
	}
}
