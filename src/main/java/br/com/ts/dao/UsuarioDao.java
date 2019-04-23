package br.com.ts.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.ts.domain.Usuario;

@Repository
public interface UsuarioDao extends JpaRepository<Usuario, Long> {

	Usuario findByEmail(String email);
	Usuario findByNome(String nome);
	Usuario findByEstado(String estado);
	
	//@Query(value="SELECT * FROM Usuario u where u.email = :email and u.status = 'A'", nativeQuery=true) //SQL NATIVO
	@Query("SELECT u FROM Usuario u where u.email = :email and u.status = 'A'")
	Usuario buscaPorEmail(@Param("email") String email);
	
	@Query("SELECT u FROM Usuario u where u.nome like :nome and u.status = 'A'")
	Usuario listaPorNome(@Param("nome") String nome);
	
	@Query("SELECT u FROM Usuario u where u.estado = :estado and u.status = 'A'")
	Usuario listaPorEstado(@Param("estado") String estado);
	
	//TODO falta implementar o método lista por filtros
	
	/*
	public Usuario buscaPorEmail(String email) {
		TypedQuery<Usuario> q = getEntityManager().createNamedQuery("busca.porEmail", Usuario.class); 
		q.setParameter("email", email);
		return q.getSingleResult();
	}
	
	public List<Usuario> listaPorNome(String nome) {
		TypedQuery<Usuario> q = getEntityManager().createNamedQuery("lista.porNome", Usuario.class); 
		q.setParameter("nome", nome);
		return q.getResultList();
	}
	
	public List<Usuario> listaPorEstado(String estado) {
		TypedQuery<Usuario> q = getEntityManager().createNamedQuery("lista.porEstado", Usuario.class); 
		q.setParameter("estado", estado);
		return q.getResultList();
	}

	public List<Usuario> listaPorFiltro(Usuario usuario) {
		String query = "SELECT u FROM Usuario u where 1=1";
		if (usuario.getNome() != null) {
			query = query + " and u.nome like :nome";
		}
		if (usuario.getEmail() != null) {
			query = query + " and u.email = :email";
		}
		if (usuario.getEstado() != null) {
			query = query + " and u.estado = :estado";
		}
		TypedQuery<Usuario> q = getEntityManager().createQuery(query, Usuario.class); 
		if (usuario.getNome() != null) {
			q.setParameter("nome", usuario.getNome());
		}
		if (usuario.getEmail() != null) {
			q.setParameter("email", usuario.getEmail());
		}
		if (usuario.getEstado() != null) {
			q.setParameter("estado", usuario.getEstado());
		}
		return q.getResultList();
	}
	*/
}
