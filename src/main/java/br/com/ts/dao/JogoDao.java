package br.com.ts.dao;

import java.util.List;

import br.com.ts.domain.Jogo;

public interface JogoDao {
	
	void save(Jogo jogo);
	
	void update(Jogo Jogo);
	
	void delete(Long id);
	
	Jogo findById(Long id);
	
	List<Jogo> findAll();
	
	List<Jogo> listaPorUsuario(Long id);

	List<Jogo> listaUltimosJogosPorUsuario(Long id, int qtd);

	int countJogosRealizadoPorUsuario(Jogo jogo);

}
