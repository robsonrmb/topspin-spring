package br.com.ts.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ts.domain.Estatistica;

@Repository
public interface EstatisticaDao extends JpaRepository<Estatistica, Long> {
	/*
	public List<Estatistica> buscaPorUsuarioAnoTipoEResposta(long idUsuario, int ano, long idTipoEstatistica, long idTipoResposta) {
		String query = "SELECT e FROM Estatistica e where 1=1";
		if (idUsuario != 0) {
			query = query + " and e.usuario.id = :idUsuario";
		}
		if (ano != 0) {
			query = query + " and e.ano = :ano";
		}
		if (idTipoEstatistica != 0) {
			query = query + " and e.idTipoEstatistica = :idTipoEstatistica";
		}
		if (idTipoResposta != 0) {
			query = query + " and e.idTipoResposta = :idTipoResposta";
		}
		TypedQuery<Estatistica> q = getEntityManager().createQuery(query, Estatistica.class); 
		if (idUsuario != 0) {
			q.setParameter("idUsuario", idUsuario);
		}
		if (ano != 0) {
			q.setParameter("ano", ano);
		}
		if (idTipoEstatistica != 0) {
			q.setParameter("idTipoEstatistica", idTipoEstatistica);
		}
		if (idTipoResposta != 0) {
			q.setParameter("idTipoResposta", idTipoResposta);
		}
		return q.getResultList();
	}
	 */
}
