package br.com.ts.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.ts.domain.Amigo;

@Repository
public interface AmigoDao extends JpaRepository<Amigo, Long> {
	
	@Query("SELECT a FROM Amigo a where a.usuario.id = :idUsuario and a.amigo.id = :idAmigo")
	Amigo buscaAmigo(@Param("idUsuario") Long idUsuario, @Param("idAmigo") Long idAmigo);
	
	@Query("SELECT a FROM Amigo a where a.usuario.id = :id")
	List<Amigo> listaAmigos(@Param("id") Long id);
	
	/*
	public Amigo buscaAmigo(FormUsuarioAmigo formUsuarioAmigo) {
		TypedQuery<Amigo> q = getEntityManager().createNamedQuery("busca.porUsuarioEAmigo", Amigo.class); 
		q.setParameter("idUsuario", formUsuarioAmigo.getIdUsuario());
		q.setParameter("idAmigo", formUsuarioAmigo.getIdAmigo());
		List<Amigo> l = q.getResultList();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}else {
			return null;
		}
	}
	
	public List<Amigo> listaAmigos(Usuario usuario) {
		TypedQuery<Amigo> q = getEntityManager().createNamedQuery("lista.porUsuario", Amigo.class); 
		q.setParameter("id", usuario.getId());
		return q.getResultList();
	}
	*/
}
