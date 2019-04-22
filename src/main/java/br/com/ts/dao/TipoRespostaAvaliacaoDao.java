package br.com.ts.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.ts.domain.TipoRespostaAvaliacao;

@Repository
public interface TipoRespostaAvaliacaoDao extends JpaRepository<TipoRespostaAvaliacao, Long> {
	
	TipoRespostaAvaliacao findByNome(String nome);
	
	@Query("SELECT tra FROM TipoRespostaAvaliacao tra where 1=1 and tra.nome = :nome")
	TipoRespostaAvaliacao buscaPorNome(@Param("nome") String nome);
	
	/*
	public TipoRespostaAvaliacao buscaPorNome(String nome) {
		String query = "SELECT tra FROM TipoRespostaAvaliacao tra where 1=1 and tra.nome = :nome";
		TypedQuery<TipoRespostaAvaliacao> q = getEntityManager().createQuery(query, TipoRespostaAvaliacao.class); 
		q.setParameter("nome", nome);
		return q.getSingleResult();
	}
	*/
}
