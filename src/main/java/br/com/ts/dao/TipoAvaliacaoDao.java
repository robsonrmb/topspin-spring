package br.com.ts.dao;

import javax.persistence.TypedQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ts.domain.TipoAvaliacao;

@Repository
public interface TipoAvaliacaoDao extends JpaRepository<TipoAvaliacao, Long> {
	/*
	public TipoAvaliacao buscaPorNome(String nome) {
		String query = "SELECT ta FROM TipoAvaliacao ta where 1=1 and ta.nome = :nome";
		TypedQuery<TipoAvaliacao> q = getEntityManager().createQuery(query, TipoAvaliacao.class); 
		q.setParameter("nome", nome);
		return q.getSingleResult();
	}
	*/
}
