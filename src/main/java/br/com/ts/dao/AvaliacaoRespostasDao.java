package br.com.ts.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ts.domain.AvaliacaoRespostas;

@Repository
public interface AvaliacaoRespostasDao extends JpaRepository<AvaliacaoRespostas, Long> {

}
