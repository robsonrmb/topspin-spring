package br.com.ts.dao;

import java.util.List;

import br.com.ts.domain.Estatistica;

public interface EstatisticaDao {
	
	void save(Estatistica estatistica);
	
	void update(Estatistica estatistica);
	
	List<Estatistica> buscaPorUsuarioAnoTipoEResposta(long idUsuario, int ano, long idTipoEstatistica, long idTipoRespostaEstatistica);
	
}
