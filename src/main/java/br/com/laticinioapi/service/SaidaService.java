package br.com.laticinioapi.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.laticinioapi.entity.Saida;
import br.com.laticinioapi.repository.SaidaRepository;

@Service
public class SaidaService {
	
	@Autowired
	private SaidaRepository saidaRepository;
	
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

}
