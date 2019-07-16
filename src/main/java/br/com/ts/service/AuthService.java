package br.com.ts.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ts.dao.UsuarioDao;
import br.com.ts.domain.Usuario;
import br.com.ts.service.exception.ObjectNotFoundException;
import br.com.ts.service.mail.EmailService;

@Service
@Transactional(readOnly = false)
public class AuthService {
	
	private Random rand = new Random();
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private EmailService emailService;
	
	// MÉTODO QUE ATUALIZA UM NOVA SENHA PARA O USUÁRIO ENVIANDO O EMAIL COM A NOVA SENHA.
	public void sendNewPassword(String email) {
		
		Usuario usuario = usuarioService.buscaPorEmail(email);
		if (usuario == null) {
			throw new ObjectNotFoundException("Email não encontrado.");
		}
		String novaSenha = novaSenha();
		novaSenha = "123";
		usuario.setSenha(pe.encode(novaSenha));
		usuarioDao.save(usuario);
		
		emailService.sendNewPasswordEmail(usuario, novaSenha);
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
