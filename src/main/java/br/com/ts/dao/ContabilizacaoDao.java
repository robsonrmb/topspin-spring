package br.com.ts.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ts.domain.Contabilizacao;

@Repository
public interface ContabilizacaoDao extends JpaRepository<Contabilizacao, Long> {
	/*
	public List<Contabilizacao> buscaPorUsuarioAno(long idUsuario, int ano) {
		String query = "SELECT c FROM Contabilizacao c where 1=1";
		if (idUsuario != 0) {
			query = query + " and c.usuario.id = :idUsuario";
		}
		if (ano != 0) {
			query = query + " and c.ano = :ano";
		}
		TypedQuery<Contabilizacao> q = getEntityManager().createQuery(query, Contabilizacao.class); 
		if (idUsuario != 0) {
			q.setParameter("idUsuario", idUsuario);
		}
		if (ano != 0) {
			q.setParameter("ano", ano);
		}
		return q.getResultList();
	}
	*/
}
