package br.com.ts.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ts.dao.AcessoDao;
import br.com.ts.dao.UsuarioDao;
import br.com.ts.dao.dinamic.AcessoDaoImpl;
import br.com.ts.domain.Acesso;
import br.com.ts.domain.Usuario;
import br.com.ts.dto.CadastroLoginDTO;

@Service @Transactional(readOnly = false)
public class AcessoService {

	@Autowired
	private AcessoDao acessoDao;
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private AcessoDaoImpl acessoDaoImpl;
	
	public Usuario insert(CadastroLoginDTO cadastroLoginDTO) {
		
		Acesso acesso = new Acesso(cadastroLoginDTO.getEmail(), cadastroLoginDTO.getSenha());
		acesso.setId(null);
		acessoDao.save(acesso);
		
		Usuario usuario = new Usuario(cadastroLoginDTO.getNome(), cadastroLoginDTO.getEmail(), cadastroLoginDTO.getEstado(), cadastroLoginDTO.getSexo(), "A");
		usuario.setId(null);
		usuarioDao.save(usuario);
		
		return usuario;
	}

	public void update(Acesso usuario) {
		acessoDao.save(usuario);
	}

	public void delete(Long id) {
		acessoDao.deleteById(id);
	}

	@Transactional(readOnly = true)
	public Acesso buscaPorId(Long id) {
		return acessoDao.findById(id).get();
	}

	public boolean isExisteUsuario(Acesso acesso) {
		return acessoDaoImpl.isExisteUsuario(acesso);
		//throw new UnsupportedOperationException();
	}

}
