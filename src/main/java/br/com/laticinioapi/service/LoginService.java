package br.com.laticinioapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.laticinioapi.entity.Login;
import br.com.laticinioapi.repository.LoginRepository;

@Service
public class LoginService {
	
	@Autowired
	private LoginRepository loginRepository;
	
	
	public boolean verificaLogin(String login, String senha) {
		
		var loginList = loginRepository.findAll();
		
		for (Login loginBd : loginList) {
			if(loginBd.getLogin().equalsIgnoreCase(login)){
				if(loginBd.getSenha().equalsIgnoreCase(senha)){
					return true;
				}
			}else {
				return false;
			}
		}
		
		return false;
	}

}
