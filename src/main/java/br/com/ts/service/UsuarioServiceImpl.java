package br.com.ts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ts.bean.FormUsuarioAmigo;
import br.com.ts.dao.AmigoDaoImpl;
import br.com.ts.dao.UsuarioDao;
import br.com.ts.domain.Amigo;
import br.com.ts.domain.Usuario;

@Service 
@Transactional(readOnly = false)
public class UsuarioServiceImpl {

	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private AmigoDaoImpl amigoDao;
	
	public void salva(Usuario usuario) {
		usuarioDao.save(usuario);
	}

	public void atualiza(Usuario usuario) {
		usuarioDao.update(usuario);
	}

	public void exclui(Long id) {
		usuarioDao.delete(id);
	}

	@Transactional(readOnly = true)
	public Usuario buscaPorId(Long id) {
		return usuarioDao.findById(id);
	}

	@Transactional(readOnly = true)
	public List<Usuario> listaTodos() {
		return usuarioDao.findAll();
	}
	
	@Transactional(readOnly = true)
	public Usuario buscaPorEmail(String email) {
		return usuarioDao.buscaPorEmail(email);
	}
	
	@Transactional(readOnly = true)
	public List<Usuario> listaPorNome(String nome) {
		return usuarioDao.listaPorNome(nome);
	}
	
	@Transactional(readOnly = true)
	public List<Usuario> listaPorEstado(String estado) {
		return usuarioDao.listaPorEstado(estado);
	}

	public List<Usuario> listaPorFiltro(Usuario usuario) {
		return usuarioDao.listaPorFiltro(usuario);
	}
	
	public List<Usuario> listaPorFiltroComFlagAmigo(Usuario usuario) {
		List<Usuario> lista = usuarioDao.listaPorFiltro(usuario);
		for (Usuario u: lista) {
			FormUsuarioAmigo formUsuarioAmigo = new FormUsuarioAmigo(usuario.getId(), u.getId());
			Amigo amigo = amigoDao.buscaAmigo(formUsuarioAmigo);
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
