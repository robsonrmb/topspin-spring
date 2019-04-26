package br.com.ts.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.ts.dao.UsuarioDao;
import br.com.ts.domain.Usuario;
import br.com.ts.security.UserSS;

/*
 * INTERFACE QUE REALIZA A BUSCA CONFORME EMAIL DO USUÁRIO.
 * RECEBE O USUÁRIO E RETORNA UM UserDetail, NESTE CASO, O UserSS.
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UsuarioDao usuarioDao;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = usuarioDao.buscaPorEmail(email);
		if (usuario == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSS(usuario.getId(), usuario.getEmail(), usuario.getSenha(), usuario.getPerfis());
	}

}
