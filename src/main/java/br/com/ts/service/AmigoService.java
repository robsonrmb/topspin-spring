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
import br.com.ts.service.exception.DataIntegrityException;
import br.com.ts.service.exception.RegraNegocioException;

@Service 
@Transactional(readOnly = false)
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
		
		Amigo amig = amigoDao.buscaAmigo(usuario.getId(), uAmigo.getId());
		if (amig != null) {
			throw new RegraNegocioException("Este usuário já está cadastrado como um amigo!!!");
		}
		
		amigoDao.save(amigo);
	}

	public void delete(Long id) {
		amigoDao.deleteById(id);
	}
	
	public void delete(UsuarioAmigoDTO usuarioAmigoDTO) {
		Amigo amigo = amigoDao.buscaAmigo(usuarioAmigoDTO.getIdUsuario(), usuarioAmigoDTO.getIdAmigo());
		amigoDao.deleteById(amigo.getId());
	}

	@Transactional(readOnly = true)
	public List<Usuario> listaAmigos(Long id) {
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		List<Amigo> listaAmigos = amigoDao.listaAmigos(id);
		for (Amigo amigo: listaAmigos) {
			Usuario uAmigo = amigo.getAmigo();
			listaUsuarios.add(uAmigo);
		}
		return listaUsuarios;
	}

}
