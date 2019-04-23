package br.com.ts.dao.dinamic;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.ts.domain.Usuario;

@Repository
public class UsuarioDaoImpl extends AbstractDao<Usuario, Long> {

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

}
