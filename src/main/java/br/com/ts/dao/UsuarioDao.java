package br.com.ts.dao;

import java.util.List;

import br.com.ts.domain.Usuario;

public interface UsuarioDao {
	
	void save(Usuario usuario);
	
	void update(Usuario usuario);
	
	void delete(Long id);
	
	Usuario findById(Long id);
	
	List<Usuario> findAll();

	Usuario buscaPorEmail(String email);
	
	List<Usuario> listaPorNome(String nome);
	
	List<Usuario> listaPorEstado(String estado);

	List<Usuario> listaPorFiltro(Usuario usuario);

}
