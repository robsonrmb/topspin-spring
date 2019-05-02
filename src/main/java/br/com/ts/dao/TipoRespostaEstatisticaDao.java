package br.com.ts.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.ts.domain.TipoRespostaEstatistica;

@Repository
public interface TipoRespostaEstatisticaDao extends JpaRepository<TipoRespostaEstatistica, Long> {
	
	TipoRespostaEstatistica findByNome(String nome);
	
	@Query("SELECT tre FROM TipoRespostaEstatistica tre where 1=1 and tre.nome = :nome")
	TipoRespostaEstatistica buscaPorNome(@Param("nome") String nome);

}
