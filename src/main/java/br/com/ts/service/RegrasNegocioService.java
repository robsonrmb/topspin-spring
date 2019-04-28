package br.com.ts.service;

import br.com.ts.domain.Usuario;
import br.com.ts.security.UserSS;
import br.com.ts.service.exception.AuthorizationException;

public class RegrasNegocioService {

	public static void umUsuarioNaoPodeAvaliarEleMesmo(Usuario avaliado) {
		UserSS usuarioLogado = AuthenticationService.getUsuarioAutenticado();
		if (usuarioLogado == null || (usuarioLogado.getEmail().equals(avaliado.getEmail()))) {
			throw new AuthorizationException("Acesso negado!!! Um usuário não pode avaliar a si mesmo.");
		}
	}
	
	public static void umUsuarioNaoPodeAvaliarPorOutroUsuario(Usuario usuario) {
		UserSS usuarioLogado = AuthenticationService.getUsuarioAutenticado();
		if (usuarioLogado == null || (!usuarioLogado.getEmail().equals(usuario.getEmail()))) {
			throw new AuthorizationException("Acesso negado!!! Um usuário não pode avaliar por outro usuário.");
		}
	}
	
	public static void umUsuarioNaoPodeConvidarEleMesmo(Usuario convidado) {
		UserSS usuarioLogado = AuthenticationService.getUsuarioAutenticado();
		if (usuarioLogado == null || (usuarioLogado.getEmail().equals(convidado.getEmail()))) {
			throw new AuthorizationException("Acesso negado!!! Um usuário não pode convidar a si mesmo.");
		}
	}
	
	public static void umUsuarioNaoPodeConvidarPorOutroUsuario(Usuario usuario) {
		UserSS usuarioLogado = AuthenticationService.getUsuarioAutenticado();
		if (usuarioLogado == null || (!usuarioLogado.getEmail().equals(usuario.getEmail()))) {
			throw new AuthorizationException("Acesso negado!!! Um usuário não pode convidar por outro usuário.");
		}
	}

	public static void umUsuarioSoPodeAlterarSeuProprioCadastro(Usuario usuario) {
		UserSS usuarioLogado = AuthenticationService.getUsuarioAutenticado();
		if (usuarioLogado == null || (!usuarioLogado.getEmail().equals(usuario.getEmail()))) {
			throw new AuthorizationException("Acesso negado!!! Um usuário não pode alterar o cadastro de outro usuário.");
		}
	}

	public static void umUsuarioSoPodeCadastrarJogoDeleMesmo(Usuario usuario) {
		UserSS usuarioLogado = AuthenticationService.getUsuarioAutenticado();
		if (usuarioLogado == null || (!usuarioLogado.getEmail().equals(usuario.getEmail()))) {
			throw new AuthorizationException("Acesso negado!!! Um usuário não pode cadastrar jogo de outro usuário.");
		}
	}
	
}
