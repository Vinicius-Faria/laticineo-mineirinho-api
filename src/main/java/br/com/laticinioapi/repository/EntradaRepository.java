package br.com.laticinioapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.laticinioapi.entity.Entrada;

public interface EntradaRepository extends JpaRepository<Entrada, Long>{

}
