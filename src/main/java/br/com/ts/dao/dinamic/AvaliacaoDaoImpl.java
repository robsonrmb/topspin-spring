package br.com.ts.dao.dinamic;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.ts.domain.Avaliacao;

@Repository
public class AvaliacaoDaoImpl extends AbstractDao<Avaliacao, Long> {

	public List<Avaliacao> listaPorAvaliadoEStatus(Avaliacao avaliacao) {
		String query = "SELECT a FROM Avaliacao a where 1=1";
		if (avaliacao.getAvaliado() != null && avaliacao.getAvaliado().getId() != 0) {
			query = query + " and a.avaliado.id = :avaliado";
		}
		if (avaliacao.getStatus() != null) {
			query = query + " and a.status = :status";
		}
		TypedQuery<Avaliacao> q = getEntityManager().createQuery(query, Avaliacao.class); 
		if (avaliacao.getAvaliado() != null && avaliacao.getAvaliado().getId() != 0) {
			q.setParameter("avaliado", avaliacao.getAvaliado().getId());
		}
		if (avaliacao.getStatus() != null) {
			q.setParameter("status", avaliacao.getStatus());
		}
		return q.getResultList();
	}
	
	public int countPorAvaliadoEPendente(Avaliacao avaliacao) {
		String query = "SELECT count(a) FROM Avaliacao a where 1=1 and a.status = 'P'";
		if (avaliacao.getAvaliado() != null && avaliacao.getAvaliado().getId() != 0) {
			query = query + " and a.avaliado.id = :avaliado";
		}
		TypedQuery<Long> q = getEntityManager().createQuery(query, Long.class); 
		if (avaliacao.getAvaliado() != null && avaliacao.getAvaliado().getId() != 0) {
			q.setParameter("avaliado", avaliacao.getAvaliado().getId());
		}
		long valor = q.getSingleResult();
		return Integer.parseInt(valor+"");
	}
	
}
