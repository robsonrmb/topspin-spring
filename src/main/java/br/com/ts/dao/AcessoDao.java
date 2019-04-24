package br.com.ts.dao;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.com.ts.domain.Acesso;

@Repository
public interface AcessoDao extends JpaRepository<Acesso, Long> { //, JpaSpecificationExecutor<Acesso>
	
	//boolean isExisteUsuario(Specification<Acesso> spec); //,Pageable pageable ou Sort sort
	
	/*
	public boolean isExisteUsuario(Acesso acesso) {
		
		TypedQuery<Acesso> query = getEntityManager().createQuery("from Acesso where email = :email and senha = :senha", Acesso.class);
		query.setParameter("email", acesso.getEmail());
		query.setParameter("senha", acesso.getSenha());
		List<Acesso> listaDeAcesso = query.getResultList();
		
		if (listaDeAcesso == null || listaDeAcesso.size() == 0) {
			return false;
		}else{
			return true;
		}
		
	}
 	*/
}
