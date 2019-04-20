package br.com.ts.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ts.domain.Amigo;
import br.com.ts.domain.Usuario;
import br.com.ts.dto.UsuarioAmigoDTO;

@Repository
public interface AmigoDao extends JpaRepository<Amigo, Long> {
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
