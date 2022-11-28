package br.com.laticinioapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.laticinioapi.service.LoginService;

@RestController
@RequestMapping(value = "/login")
@CrossOrigin("https://emporiomineirinho.vercel.app/")
//@CrossOrigin("*")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@GetMapping
	public boolean verificaLogin(
			@RequestParam(required = false) String login, 
			@RequestParam(required = true) String senha) {
		
		return loginService.verificaLogin(login, senha);
		
	}

}
