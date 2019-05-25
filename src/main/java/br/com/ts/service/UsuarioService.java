package br.com.ts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ts.dao.AmigoDao;
import br.com.ts.dao.UsuarioDao;
import br.com.ts.dao.dinamic.UsuarioDaoImpl;
import br.com.ts.domain.Amigo;
import br.com.ts.domain.Usuario;
import br.com.ts.dto.CadastroLoginDTO;
import br.com.ts.dto.UserPassDTO;
import br.com.ts.dto.UsuarioAmigoDTO;
import br.com.ts.service.exception.AuthorizationException;
import br.com.ts.service.exception.DataIntegrityException;
import br.com.ts.service.exception.ObjectNotFoundException;
import br.com.ts.service.exception.RegraNegocioException;

@Service 
@Transactional(readOnly = false)
public class UsuarioService {

	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private UsuarioDaoImpl usuarioDaoImpl;
	
	@Autowired
	private AmigoDao amigoDao;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	public void insert(Usuario usuario) {
		usuario.setId(null);
		usuarioDao.save(usuario);
	}
	
	public Usuario insert(CadastroLoginDTO cadastroLoginDTO) {
		
		Usuario usuario = new Usuario(cadastroLoginDTO.getNome(), cadastroLoginDTO.getEmail(), pe.encode(cadastroLoginDTO.getSenha()), cadastroLoginDTO.getEstado(), cadastroLoginDTO.getSexo(), "A");
		usuario.setId(null);
		
		Usuario u = buscaPorEmail(usuario.getEmail());
		if (u == null) {
			usuarioDao.save(usuario);
		}else {
			throw new RegraNegocioException("Email já cadastrado!!!");
		}
		
		return usuario;
	}
	
	public boolean isExisteUsuario(Usuario usuario) {
		return usuarioDaoImpl.isExisteUsuario(usuario);
		//throw new UnsupportedOperationException();
	}

	public void update(Usuario usuario) {
		Usuario usuarioBanco = find(usuario.getId());
		RegrasNegocioService.umUsuarioSoPodeAlterarSeuProprioCadastro(usuarioBanco);
		parse(usuarioBanco, usuario);
		usuarioDao.save(usuarioBanco);
	}

	private void parse(Usuario usuarioBanco, Usuario usuarioNovo) {
		usuarioBanco.setNome(usuarioNovo.getNome());
		usuarioBanco.setApelido(usuarioNovo.getApelido());
		usuarioBanco.setDataNascimento(usuarioNovo.getDataNascimento());
		usuarioBanco.setEstado(usuarioNovo.getEstado());
		usuarioBanco.setCidade(usuarioNovo.getCidade());
		usuarioBanco.setOndeJoga(usuarioNovo.getOndeJoga());
		usuarioBanco.setSexo(usuarioNovo.getSexo());
		usuarioBanco.setTipo(usuarioNovo.getTipo());
		usuarioBanco.setNivel(usuarioNovo.getNivel());
		usuarioBanco.setSenha(pe.encode(usuarioNovo.getSenha()));
	}
	
	public void atualizaSenha(UserPassDTO userPassDTO) {
		Usuario usuarioBanco = usuarioDao.buscaPorEmail(userPassDTO.getEmail());
		/*if (!pe.encode(userPassDTO.getSenha()).equals(usuarioBanco.getSenha())) {
			throw new AuthorizationException("Senha atual incorreta.");
		}*/
		RegrasNegocioService.umUsuarioSoPodeAlterarSeuProprioCadastro(usuarioBanco);
		usuarioBanco.setSenha(pe.encode(userPassDTO.getNovaSenha()));
		usuarioDao.save(usuarioBanco);
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
		return usuarioDao.buscaPorEmail(email);
	}
	
	@Transactional(readOnly = true)
	public List<Usuario> listaPorNome(String nome) {
		return usuarioDaoImpl.listaPorNome(nome);
	}
	
	@Transactional(readOnly = true)
	public List<Usuario> listaPorEstado(String estado) {
		return usuarioDaoImpl.listaPorEstado(estado);
	}

	public List<Usuario> listaPorFiltro(Usuario usuario) {
		return usuarioDaoImpl.listaPorFiltro(usuario);
	}
	
	public List<Usuario> listaPorFiltroComFlagAmigo(Usuario usuario) {
		List<Usuario> lista = usuarioDaoImpl.listaPorFiltro(usuario);
		for (Usuario u: lista) {
			UsuarioAmigoDTO usuarioAmigoDTO = new UsuarioAmigoDTO(usuario.getId(), u.getId());
			Amigo amigo = amigoDao.buscaAmigo(usuarioAmigoDTO.getIdUsuario(), usuarioAmigoDTO.getIdAmigo());
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
