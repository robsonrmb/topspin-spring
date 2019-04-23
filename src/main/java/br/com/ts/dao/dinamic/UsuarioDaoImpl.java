package br.com.ts.dao.dinamic;

import java.util.List;
import java.util.Optional;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.ts.domain.Acesso;
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
	
	public List<Usuario> listOfFilter(Usuario usuario) {
		StringBuilder query = new StringBuilder("SELECT u FROM Usuario u where 1=1");
		
		Optional<String> nomeUsuario = Optional.ofNullable(usuario.getNome());
		Optional<String> emailUsuario = Optional.ofNullable(usuario.getEmail());
		Optional<String> estadoCivilUsuario = Optional.ofNullable(usuario.getEstado());
		
		nomeUsuario.ifPresent(nome -> query.append(" AND lower(u.nome) like lower(:nome) "));
		emailUsuario.ifPresent(email -> query.append(" AND u.email = :email "));
		estadoCivilUsuario.ifPresent(estadoCivil ->	query.append(" AND u.estadoCivil = :estadoCivil "));
		
		TypedQuery<Usuario> typedQuery = getEntityManager().createQuery(query.toString(), Usuario.class);
		
		nomeUsuario.ifPresent(nome -> typedQuery.setParameter("nome", nome));
		emailUsuario.ifPresent(email -> typedQuery.setParameter("email", email));
		estadoCivilUsuario.ifPresent(estadoCivil -> typedQuery.setParameter("estadoCivil", estadoCivil));
		
		List<Usuario> usuarios = typedQuery.getResultList();
		
		return Optional.ofNullable(usuarios).filter(lista -> !lista.isEmpty()).get();
	}

}
