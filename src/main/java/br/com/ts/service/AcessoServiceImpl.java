package br.com.ts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ts.bean.FormCadastroLogin;
import br.com.ts.dao.AcessoDao;
import br.com.ts.dao.UsuarioDao;
import br.com.ts.domain.Acesso;
import br.com.ts.domain.Usuario;

@Service @Transactional(readOnly = false)
public class AcessoServiceImpl {

	@Autowired
	private AcessoDao acessoDao;
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	public void salva(FormCadastroLogin formCadastroLogin) {
		
		Acesso acesso = new Acesso(formCadastroLogin.getEmail(), formCadastroLogin.getSenha());
		acessoDao.save(acesso);
		
		Usuario usuario = new Usuario(formCadastroLogin.getNome(), formCadastroLogin.getEmail(), formCadastroLogin.getEstado(), formCadastroLogin.getSexo(), "A");
		usuarioDao.save(usuario);
	}

	public void atualiza(Acesso usuario) {
		acessoDao.update(usuario);
	}

	public void exclui(Long id) {
		acessoDao.delete(id);
	}

	@Transactional(readOnly = true)
	public Acesso buscaPorId(Long id) {
		return acessoDao.findById(id);
	}

	public boolean isExisteUsuario(Acesso acesso) {
		return acessoDao.isExisteUsuario(acesso);
	}

}
