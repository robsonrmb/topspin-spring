package br.com.ts.dao;

import java.util.List;

import br.com.ts.domain.Avaliacao;

public interface AvaliacaoDao {
	
	void save(Avaliacao avaliacao);
	
	void update(Avaliacao avaliacao);
	
	void delete(Long id);
	
	Avaliacao findById(Long id);

	List<Avaliacao> listaPorAvaliadoEStatus(Avaliacao avaliacao);
	
	int countPorAvaliadoEPendente(Avaliacao avaliacao);

}
