package br.com.ts.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ts.domain.Convite;

@Repository
public interface ConviteDao extends JpaRepository<Convite, Long> {
	
}
