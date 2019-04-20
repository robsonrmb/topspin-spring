package br.com.ts.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ts.dao.AcessoDao;
import br.com.ts.dao.UsuarioDao;
import br.com.ts.domain.Acesso;
import br.com.ts.domain.Usuario;
import br.com.ts.dto.CadastroLoginDTO;

@Service @Transactional(readOnly = false)
public class AcessoService {

	@Autowired
	private AcessoDao acessoDao;
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	public void salva(CadastroLoginDTO formCadastroLogin) {
		
		Acesso acesso = new Acesso(formCadastroLogin.getEmail(), formCadastroLogin.getSenha());
		acessoDao.save(acesso);
		
		Usuario usuario = new Usuario(formCadastroLogin.getNome(), formCadastroLogin.getEmail(), formCadastroLogin.getEstado(), formCadastroLogin.getSexo(), "A");
		usuarioDao.save(usuario);
	}

	public void atualiza(Acesso usuario) {
		acessoDao.save(usuario);
	}

	public void exclui(Long id) {
		acessoDao.deleteById(id);
	}

	@Transactional(readOnly = true)
	public Optional<Acesso> buscaPorId(Long id) {
		return acessoDao.findById(id);
	}

	public boolean isExisteUsuario(Acesso acesso) {
		return true; //TODO acessoDao.isExisteUsuario(acesso);
	}

}
