package br.com.ts.dao;

import javax.persistence.TypedQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ts.domain.TipoEstatistica;

@Repository
public interface TipoEstatisticaDao extends JpaRepository<TipoEstatistica, Long> {
	/*
	public TipoEstatistica buscaPorNome(String nome) {
		String query = "SELECT te FROM TipoEstatistica te where 1=1 and te.nome = :nome";
		TypedQuery<TipoEstatistica> q = getEntityManager().createQuery(query, TipoEstatistica.class); 
		q.setParameter("nome", nome);
		return q.getSingleResult();
	}
	*/
}
