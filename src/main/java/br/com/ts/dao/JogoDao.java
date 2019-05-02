package br.com.ts.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ts.domain.Jogo;

@Repository
public interface JogoDao extends JpaRepository<Jogo, Long> {
	
}
