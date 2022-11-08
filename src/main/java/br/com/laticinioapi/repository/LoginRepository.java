package br.com.laticinioapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.laticinioapi.entity.Login;

public interface LoginRepository extends JpaRepository<Login, Long>{

}
