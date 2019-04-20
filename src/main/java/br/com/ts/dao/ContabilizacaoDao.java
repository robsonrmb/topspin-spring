package br.com.ts.dao;

import java.util.List;

import br.com.ts.domain.Contabilizacao;

public interface ContabilizacaoDao {
	
	void save(Contabilizacao contabilizacao);
	
	void update(Contabilizacao contabilizacao);
	
	List<Contabilizacao> buscaPorUsuarioAno(long idUsuario, int ano);
	
}
