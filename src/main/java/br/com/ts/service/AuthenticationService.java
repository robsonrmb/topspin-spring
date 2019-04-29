package br.com.ts.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.ts.domain.Usuario;
import br.com.ts.security.UserSS;
import br.com.ts.service.exception.ObjectNotFoundException;

public class AuthenticationService {
	
	private Random rand = new Random();
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	// MÉTODO QUE RETORNA O USUÁRIO LOGADO
	public static UserSS getUsuarioAutenticado() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		}catch (Exception e) {
			return null;
		}
	}
	
	// MÉTODO QUE ATUALIZA UM NOVA SENHA PARA O USUÁRIO ENVIANDO O EMAIL COM A NOVA SENHA.
	public void sendNewPassword(String email) {
		
		Usuario usuario = usuarioService.buscaPorEmail(email);
		if (usuario == null) {
			throw new ObjectNotFoundException("Email não encontrado.");
		}
		String novaSenha = novaSenha();
		usuario.setSenha(pe.encode(novaSenha));
	}

	private String novaSenha() {
		char[] vet = new char[10];
		for (int i=0; i<10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(3);
		if (opt == 0) { //gera um dígito.
			return (char) (rand.nextInt(10) + 48);
			
		}else if (opt == 1) { //gera uma letra maiúscula.
			return (char) (rand.nextInt(26) + 65);
			
		}else{//gera uma letra minúscula
			return (char) (rand.nextInt(26) + 97);
		}
	}

}
