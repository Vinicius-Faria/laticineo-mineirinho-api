package br.com.laticinioapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.laticinioapi.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
	@Query(value = "select * from Produto limit 10", nativeQuery = true)
	public List<Produto> findByProdutoLimit();

}
