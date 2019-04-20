package br.com.ts.dao;

import javax.persistence.TypedQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ts.domain.TipoRespostaEstatistica;

@Repository
public interface TipoRespostaEstatisticaDao extends JpaRepository<TipoRespostaEstatistica, Long> {
	/*
	public TipoRespostaEstatistica buscaPorNome(String nome) {
		String query = "SELECT tre FROM TipoRespostaEstatistica tre where 1=1 and tre.nome = :nome";
		TypedQuery<TipoRespostaEstatistica> q = getEntityManager().createQuery(query, TipoRespostaEstatistica.class); 
		q.setParameter("nome", nome);
		return q.getSingleResult();
	}
	*/
}
