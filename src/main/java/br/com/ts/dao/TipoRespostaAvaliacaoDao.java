package br.com.ts.dao;

import br.com.ts.domain.TipoRespostaAvaliacao;

public interface TipoRespostaAvaliacaoDao {
	
	TipoRespostaAvaliacao findById(Long id);
	
	TipoRespostaAvaliacao buscaPorNome(String nome);

}
