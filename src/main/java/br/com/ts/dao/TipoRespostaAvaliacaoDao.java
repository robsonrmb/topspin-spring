package br.com.ts.dao;

import javax.persistence.TypedQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ts.domain.TipoRespostaAvaliacao;

@Repository
public interface TipoRespostaAvaliacaoDao extends JpaRepository<TipoRespostaAvaliacao, Long> {
	/*
	public TipoRespostaAvaliacao buscaPorNome(String nome) {
		String query = "SELECT tra FROM TipoRespostaAvaliacao tra where 1=1 and tra.nome = :nome";
		TypedQuery<TipoRespostaAvaliacao> q = getEntityManager().createQuery(query, TipoRespostaAvaliacao.class); 
		q.setParameter("nome", nome);
		return q.getSingleResult();
	}
	*/
}
