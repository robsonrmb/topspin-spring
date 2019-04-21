package br.com.ts.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ts.dao.AmigoDao;
import br.com.ts.dao.UsuarioDao;
import br.com.ts.domain.Amigo;
import br.com.ts.domain.Usuario;
import br.com.ts.dto.UsuarioAmigoDTO;

@Service @Transactional(readOnly = false)
public class AmigoService {

	@Autowired
	private AmigoDao amigoDao;
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	public void insert(UsuarioAmigoDTO formUsuarioAmigo) {
		Usuario usuario = usuarioDao.findById(formUsuarioAmigo.getIdUsuario()).get();
		Usuario uAmigo = usuarioDao.findById(formUsuarioAmigo.getIdAmigo()).get();
		
		Amigo amigo = new Amigo();
		amigo.setId(null);
		amigo.setUsuario(usuario);
		amigo.setAmigo(uAmigo);
		
		amigoDao.save(amigo);
	}

	public void delete(Long id) {
		amigoDao.deleteById(id);
	}
	
	public void delete(UsuarioAmigoDTO usuarioAmigoDTO) {
		Amigo amigo = null; //TODO amigoDao.buscaAmigo(usuarioAmigoDTO);
		amigoDao.deleteById(amigo.getId());
	}

	@Transactional(readOnly = true)
	public List<Usuario> listaAmigos(Long id) {
		Usuario usuario = new Usuario();
		usuario.setId(id);
		
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		List<Amigo> listaAmigos = new ArrayList<Amigo>(); //TODO amigoDao.listaAmigos(usuario);
		for (Amigo amigo: listaAmigos) {
			Usuario uAmigo = amigo.getAmigo();
			listaUsuarios.add(uAmigo);
		}
		return listaUsuarios;
	}

}
