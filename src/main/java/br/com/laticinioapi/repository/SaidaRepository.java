package br.com.laticinioapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.laticinioapi.entity.Saida;

public interface SaidaRepository extends JpaRepository<Saida, Long> {
	
	@Query(value = "select * from Saida order by id desc limit 1", nativeQuery = true)
	public Saida findByVendaLimit1();

}
