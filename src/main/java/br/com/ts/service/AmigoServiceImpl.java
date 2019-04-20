package br.com.ts.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ts.bean.FormUsuarioAmigo;
import br.com.ts.dao.AmigoDaoImpl;
import br.com.ts.dao.UsuarioDao;
import br.com.ts.domain.Amigo;
import br.com.ts.domain.Usuario;

@Service @Transactional(readOnly = false)
public class AmigoServiceImpl {

	@Autowired
	private AmigoDaoImpl amigoDao;
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	public void salva(FormUsuarioAmigo formUsuarioAmigo) {
		Usuario usuario = usuarioDao.findById(formUsuarioAmigo.getIdUsuario());
		Usuario uAmigo = usuarioDao.findById(formUsuarioAmigo.getIdAmigo());
		
		Amigo amigo = new Amigo();
		amigo.setUsuario(usuario);
		amigo.setAmigo(uAmigo);
		
		amigoDao.save(amigo);
	}

	public void remove(Long id) {
		amigoDao.delete(id);
	}
	
	public void remove(FormUsuarioAmigo formUsuarioAmigo) {
		Amigo amigo = amigoDao.buscaAmigo(formUsuarioAmigo);
		amigoDao.delete(amigo.getId());
	}

	@Transactional(readOnly = true)
	public List<Usuario> listaAmigos(Long id) {
		Usuario usuario = new Usuario();
		usuario.setId(id);
		
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		List<Amigo> listaAmigos = amigoDao.listaAmigos(usuario);
		for (Amigo amigo: listaAmigos) {
			Usuario uAmigo = amigo.getAmigo();
			listaUsuarios.add(uAmigo);
		}
		return listaUsuarios;
	}

}