package br.com.laticinioapi.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.laticinioapi.entity.Entrada;

public interface EntradaRepository extends JpaRepository<Entrada, Long>{
	
	@Query("from Entrada where data between :dataInicio and :dataFim")
	public List<Entrada> findByDataBetween(LocalDateTime dataInicio, LocalDateTime dataFim);
	
	@Query("from Entrada where data between :dataInicio and :dataFim and nome = :nome")
	public List<Entrada> findByDataBetweenAndNome(LocalDateTime dataInicio, LocalDateTime dataFim, String nome);
	
	@Query(value = "select quantidade from Entrada where data > :dataInicio and data < :dataFim", nativeQuery = true)
	public List<String> findQuantidadeByData(LocalDateTime dataInicio, LocalDateTime dataFim);

}
