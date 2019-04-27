package br.com.ts.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.ts.domain.Amigo;

@Repository
public interface AmigoDao extends JpaRepository<Amigo, Long> {
	
	@Query("SELECT a FROM Amigo a where a.usuario.id = :idUsuario and a.amigo.id = :idAmigo")
	Amigo buscaAmigo(@Param("idUsuario") Long idUsuario, @Param("idAmigo") Long idAmigo);
	
	@Query("SELECT a FROM Amigo a where a.usuario.id = :id")
	List<Amigo> listaAmigos(@Param("id") Long id);
	
}
