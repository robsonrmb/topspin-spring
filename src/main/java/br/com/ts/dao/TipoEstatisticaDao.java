package br.com.ts.dao;

import br.com.ts.domain.TipoEstatistica;

public interface TipoEstatisticaDao {
	
	TipoEstatistica buscaPorNome(String nome);

}
