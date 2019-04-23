package br.com.ts.dao.dinamic;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.ts.domain.TipoEstatistica;

@Repository
public class TipoEstatisticaDaoImpl extends AbstractDao<TipoEstatistica, Long> {

	public TipoEstatistica buscaPorNome(String nome) {
		String query = "SELECT te FROM TipoEstatistica te where 1=1 and te.nome = :nome";
		TypedQuery<TipoEstatistica> q = getEntityManager().createQuery(query, TipoEstatistica.class); 
		q.setParameter("nome", nome);
		return q.getSingleResult();
	}

}
