package br.com.ts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ts.dao.UsuarioDao;
import br.com.ts.dao.dinamic.UsuarioDaoImpl;
import br.com.ts.domain.Amigo;
import br.com.ts.domain.Usuario;
import br.com.ts.dto.CadastroLoginDTO;
import br.com.ts.dto.UsuarioAmigoDTO;
import br.com.ts.service.exception.DataIntegrityException;
import br.com.ts.service.exception.ObjectNotFoundException;

@Service 
@Transactional(readOnly = false)
public class UsuarioService {

	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private UsuarioDaoImpl usuarioDaoImpl;
	
	public void insert(Usuario usuario) {
		usuario.setId(null);
		usuarioDao.save(usuario);
	}
	
	public Usuario insert(CadastroLoginDTO cadastroLoginDTO) {
		
		Usuario usuario = new Usuario(cadastroLoginDTO.getNome(), cadastroLoginDTO.getEmail(), cadastroLoginDTO.getEstado(), cadastroLoginDTO.getSexo(), "A");
		usuario.setId(null);
		usuarioDao.save(usuario);
		
		return usuario;
	}
	
	public boolean isExisteUsuario(Usuario usuario) {
		return usuarioDaoImpl.isExisteUsuario(usuario);
		//throw new UnsupportedOperationException();
	}

	public void update(Usuario usuario) {
		find(usuario.getId());
		usuarioDao.save(usuario);
	}

	public void delete(Long id) {
		find(id);
		try {
			usuarioDao.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um usuário porque este possui vínculos!!!");
		}
	}

	@Transactional(readOnly = true)
	public Usuario find(Long id) {
		Usuario usuario = usuarioDao.findById(id).get();
		if (usuario == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName());
		}
		return usuario;
	}

	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		return usuarioDao.findAll();
	}
	
	public Page<Usuario> findPage(Integer pagina, Integer linhasPorPagina, String direcao, String ordenacao) {
		PageRequest pageRequest = PageRequest.of(pagina, linhasPorPagina, Direction.valueOf(direcao), ordenacao);
		return usuarioDao.findAll(pageRequest);
	}
	
	
	//PERSONALIZADOS ###########################################################
	
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
		Usuario u = find(id);
		u.setStatus("D");
		return u;
	}

}
