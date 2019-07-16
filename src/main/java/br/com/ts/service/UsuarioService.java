package br.com.ts.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
import br.com.ts.service.exception.FileException;
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
	
	@Autowired
	private S3Service s3service;
	
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
		//usuarioBanco.setSenha(pe.encode(usuarioNovo.getSenha()));
	}
	
	public void atualizaSenha(UserPassDTO userPassDTO) {
		Usuario usuarioBanco = usuarioDao.buscaPorEmail(userPassDTO.getEmail());
		
		//TODO 
		//Verificar como decodificar senha ou não exigir senha atual, pois já encontra-se dentro do cadastro.
		//if (!pe.encode(userPassDTO.getSenha()).equals(pe.usuarioBanco.getSenha())) {
		if (userPassDTO.getSenha().equals("999")) { //TODO CODIGO FIXO
			throw new AuthorizationException("Senha atual incorreta.");
		}
		RegrasNegocioService.umUsuarioSoPodeAlterarSeuProprioCadastro(usuarioBanco);
		usuarioBanco.setSenha(pe.encode(userPassDTO.getNovaSenha()));
		usuarioDao.save(usuarioBanco);
	}

	public void atualizaFotoPerfilBD(Long id, MultipartFile file) {
		try {
			String fileName = file.getOriginalFilename();
			InputStream is = file.getInputStream();
			String contentType = file.getContentType();
			
			Usuario usuario = find(id);
			RegrasNegocioService.umUsuarioSoPodeAlterarSeuProprioCadastro(usuario);
			
			byte[] bytes = IOUtils.toByteArray(is);
			usuario.setFotografia(bytes);
		
		} catch (IOException e) {
			throw new FileException("Erro ao salvar a foto de perfil do usuário.");
		}
	}
	
	public void atualizaFotoPerfilS3(Long id, MultipartFile file) {
		try {
			Usuario usuario = find(id);
			RegrasNegocioService.umUsuarioSoPodeAlterarSeuProprioCadastro(usuario);
			
			String namePicture = getNomeArquivoFinal(usuario, file);
			
			s3service.uploadFile(namePicture, file);
			usuario.setNomeFoto(namePicture);
			
		} catch (Exception e) {
			throw new FileException("Erro ao salvar a foto de perfil do usuário.");
		}
	}
	
	private String getNomeArquivoFinal(Usuario usuario, MultipartFile file) {
		return "foto_perfil_" + usuario.getId() + "_" + getPontoExtensao(file).toLowerCase();
	}

	private String getPontoExtensao(MultipartFile file) {
		return file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."), file.getOriginalFilename().length());
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

	/*
	private List<DocumentoDTO> obtemDocumentos(List<InputPart> inputPartsAssinado) {
		List<DocumentoDTO> result = new ArrayList<DocumentoDTO>();
		for (inputPart inputPart : inputPartsAssinado) {
            MultivaluedMap<String, String> headers = inputPart.getHeaders();
            String fileName = parseFileName(headers);
            try {
				InputStream istream = inputPart.getBody(InputStream.class, null);	
				byte[] bytes = IOUtils.toByteArray(istream);
				
				DocumentoDTO doc = new DocumentoDTO();
				doc.setArquivo(bytes);
				doc.setDataHoraUpload(new Date());
				doc.setTipoDocumento("Foto do perfil");
				doc.setMimeType(parseContentType(headers));
				doc.setNome(fileName);
				result.add(doc);
				
			} catch (IOException e) {
				System.out.println("Tratar exceção!!!");
			}			
         }
		return result;
	}
	
	private String parseContentType(MultivaluedMap<String, String> headers) {
		String result = headers.getFirst("Content-Type");
		return result;
	}
	*/

}
