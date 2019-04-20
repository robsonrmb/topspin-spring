package br.com.ts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ts.dao.AmigoDao;
import br.com.ts.dao.UsuarioDao;
import br.com.ts.domain.Amigo;
import br.com.ts.domain.Usuario;
import br.com.ts.dto.UsuarioAmigoDTO;

@Service 
@Transactional(readOnly = false)
public class UsuarioService {

	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private AmigoDao amigoDao;
	
	public void salva(Usuario usuario) {
		usuarioDao.save(usuario);
	}

	public void atualiza(Usuario usuario) {
		usuarioDao.save(usuario);
	}

	public void exclui(Long id) {
		usuarioDao.deleteById(id);
	}

	@Transactional(readOnly = true)
	public Usuario buscaPorId(Long id) {
		return usuarioDao.findById(id).get();
	}

	@Transactional(readOnly = true)
	public List<Usuario> listaTodos() {
		return usuarioDao.findAll();
	}
	
	@Transactional(readOnly = true)
	public Usuario buscaPorEmail(String email) {
		return null; //TODO usuarioDao.buscaPorEmail(email);
	}
	
	@Transactional(readOnly = true)
	public List<Usuario> listaPorNome(String nome) {
		return null; //TODO usuarioDao.listaPorNome(nome);
	}
	
	@Transactional(readOnly = true)
	public List<Usuario> listaPorEstado(String estado) {
		return null; //TODO usuarioDao.listaPorEstado(estado);
	}

	public List<Usuario> listaPorFiltro(Usuario usuario) {
		return null; //TODO usuarioDao.listaPorFiltro(usuario);
	}
	
	public List<Usuario> listaPorFiltroComFlagAmigo(Usuario usuario) {
		List<Usuario> lista = null; //TODO usuarioDao.listaPorFiltro(usuario);
		for (Usuario u: lista) {
			UsuarioAmigoDTO formUsuarioAmigo = new UsuarioAmigoDTO(usuario.getId(), u.getId());
			Amigo amigo = null; //TODO amigoDao.buscaAmigo(formUsuarioAmigo);
			if (amigo != null) {
				u.setAmigo(true);
			}else {
				u.setAmigo(false);
			}
		}
		return lista;
	}
	
	public Usuario desativa(Long id) {
		Usuario u = buscaPorId(id);
		u.setStatus("D");
		return u;
	}

}
