package br.com.laticinioapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.laticinioapi.entity.Login;
import br.com.laticinioapi.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
