package br.com.ts.dao.dinamic;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.ts.domain.Amigo;
import br.com.ts.domain.Usuario;
import br.com.ts.dto.UsuarioAmigoDTO;

@Repository
public class AmigoDaoImpl extends AbstractDao<Amigo, Long> {

	public Amigo buscaAmigo(UsuarioAmigoDTO usuarioAmigoDTO) {
		TypedQuery<Amigo> q = getEntityManager().createNamedQuery("busca.porUsuarioEAmigo", Amigo.class); 
		q.setParameter("idUsuario", usuarioAmigoDTO.getIdUsuario());
		q.setParameter("idAmigo", usuarioAmigoDTO.getIdAmigo());
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

}
