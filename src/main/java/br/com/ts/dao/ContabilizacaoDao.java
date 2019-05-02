package br.com.ts.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ts.domain.Contabilizacao;

@Repository
public interface ContabilizacaoDao extends JpaRepository<Contabilizacao, Long> {
	
}
