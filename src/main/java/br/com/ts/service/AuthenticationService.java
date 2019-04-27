package br.com.ts.service;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.ts.security.UserSS;

public class AuthenticationService {
	
	// MÉTODO QUE RETORNA O USUÁRIO LOGADO
	public static UserSS getUsuarioAutenticado() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		}catch (Exception e) {
			return null;
		}
	}

}
