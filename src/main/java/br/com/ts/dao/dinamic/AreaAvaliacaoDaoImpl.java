package br.com.ts.dao.dinamic;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.ts.domain.AreaAvaliacao;
import br.com.ts.domain.TipoAvaliacao;

@Repository
public class AreaAvaliacaoDaoImpl extends AbstractDao<TipoAvaliacao, Long> {

	public List<AreaAvaliacao> listaAreasAvaliacoes() {
		String query = "SELECT aa FROM AreaAvaliacao aa order by aa.id";
		TypedQuery<AreaAvaliacao> q = getEntityManager().createQuery(query, AreaAvaliacao.class); 
		return q.getResultList();
	}

}
