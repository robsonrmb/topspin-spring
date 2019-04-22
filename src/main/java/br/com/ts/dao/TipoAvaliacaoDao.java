package br.com.ts.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.ts.domain.TipoAvaliacao;

@Repository
public interface TipoAvaliacaoDao extends JpaRepository<TipoAvaliacao, Long> {
	
	TipoAvaliacao findByNome(String nome);
	
	@Query("SELECT ta FROM TipoAvaliacao ta where 1=1 and ta.nome = :nome")
	TipoAvaliacao buscaPorNome(@Param("nome") String nome);
	
	/*
	public TipoAvaliacao buscaPorNome(String nome) {
		String query = "SELECT ta FROM TipoAvaliacao ta where 1=1 and ta.nome = :nome";
		TypedQuery<TipoAvaliacao> q = getEntityManager().createQuery(query, TipoAvaliacao.class); 
		q.setParameter("nome", nome);
		return q.getSingleResult();
	}
	*/
}
