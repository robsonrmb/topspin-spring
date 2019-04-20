package br.com.ts.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ts.domain.AreaAvaliacao;
import br.com.ts.domain.TipoAvaliacao;

@Repository
public interface AreaAvaliacaoDao extends JpaRepository<TipoAvaliacao, Long> {
	/*
	@Override
	public List<AreaAvaliacao> listaAreasAvaliacaoCompleto() {
		String query = "SELECT aa FROM AreaAvaliacao aa order by aa.id";
		TypedQuery<AreaAvaliacao> q = getEntityManager().createQuery(query, AreaAvaliacao.class); 
		return q.getResultList();
	}
	*/
}
