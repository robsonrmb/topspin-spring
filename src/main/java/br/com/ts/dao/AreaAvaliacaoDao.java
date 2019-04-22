package br.com.ts.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ts.domain.AreaAvaliacao;
import br.com.ts.domain.TipoAvaliacao;

@Repository
public interface AreaAvaliacaoDao extends JpaRepository<TipoAvaliacao, Long> {
	
	@Query("SELECT aa FROM AreaAvaliacao aa order by aa.id")
	List<AreaAvaliacao> listaAreasAvaliacaoCompleto();
	
	/*
	@Override
	public List<AreaAvaliacao> listaAreasAvaliacaoCompleto() {
		String query = "SELECT aa FROM AreaAvaliacao aa order by aa.id";
		TypedQuery<AreaAvaliacao> q = getEntityManager().createQuery(query, AreaAvaliacao.class); 
		return q.getResultList();
	}
	*/
}
