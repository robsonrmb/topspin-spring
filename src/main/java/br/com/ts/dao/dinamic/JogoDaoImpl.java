package br.com.ts.dao.dinamic;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.ts.domain.Jogo;

@Repository
public class JogoDaoImpl extends AbstractDao<Jogo, Long> {

	public List<Jogo> listaPorUsuario(Long id) {
		String query = "SELECT j FROM Jogo j where 1=1";
		if (id != null && id != 0) {
			query = query + " and j.usuario.id = :usuario";
		}
		TypedQuery<Jogo> q = getEntityManager().createQuery(query, Jogo.class); 
		if (id != null && id != 0) {
			q.setParameter("usuario", id);
		}
		return q.getResultList();
	}

	public List<Jogo> listaUltimosJogosPorUsuario(Long id, int qtd) {
		String query = "SELECT j FROM Jogo j where 1=1";
		if (id != null && id != 0) {
			query = query + " and j.usuario.id = :usuario";
		}
		query = query + " order by j.data desc";
		TypedQuery<Jogo> q = getEntityManager().createQuery(query, Jogo.class); 
		if (id != null && id != 0) {
			q.setParameter("usuario", id);
		}
		q.setMaxResults(qtd);
		return q.getResultList();
	}

	public int countJogosRealizadoPorUsuario(Jogo jogo) {
		String query = "SELECT count(j) FROM Jogo j where 1=1";
		if (jogo.getUsuario() != null && jogo.getUsuario().getId() != 0) {
			query = query + " and j.usuario.id = :usuario";
		}
		TypedQuery<Long> q = getEntityManager().createQuery(query, Long.class); 
		if (jogo.getUsuario() != null && jogo.getUsuario().getId() != 0) {
			q.setParameter("usuario", jogo.getUsuario().getId());
		}
		long valor = q.getSingleResult();
		return Integer.parseInt(valor+"");
	}

}
