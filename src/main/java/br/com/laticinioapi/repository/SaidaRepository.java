package br.com.laticinioapi.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.laticinioapi.entity.Saida;

public interface SaidaRepository extends JpaRepository<Saida, Long> {
	
	@Query(value = "select * from Saida order by id desc limit 1", nativeQuery = true)
	public Saida findByVendaLimit1();
	
	@Query("from Saida where data between :dataInicio and :dataFim")
	public List<Saida> findByDataBetween(LocalDateTime dataInicio, LocalDateTime dataFim);
	
	@Query("from Saida where data between :dataInicio and :dataFim and nome = :nome")
	public List<Saida> findByDataBetweenAndNome(LocalDateTime dataInicio, LocalDateTime dataFim, String nome);
	
	@Query(value = "select quantidade from Saida where data > :dataInicio and data < :dataFim", nativeQuery = true)
	public List<String> findQuantidadeByData(LocalDateTime dataInicio, LocalDateTime dataFim);

}
