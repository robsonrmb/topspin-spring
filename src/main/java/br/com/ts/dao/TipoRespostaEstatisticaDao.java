package br.com.ts.dao;

import br.com.ts.domain.TipoRespostaEstatistica;

public interface TipoRespostaEstatisticaDao {
	
	TipoRespostaEstatistica findById(Long id);
	
	TipoRespostaEstatistica buscaPorNome(String nome);

}
