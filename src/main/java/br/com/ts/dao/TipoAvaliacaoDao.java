package br.com.ts.dao;

import br.com.ts.domain.TipoAvaliacao;

public interface TipoAvaliacaoDao {
	
	TipoAvaliacao buscaPorNome(String nome);
	
	TipoAvaliacao findById(Long id);

}
