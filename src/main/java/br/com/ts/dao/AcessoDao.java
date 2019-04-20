package br.com.ts.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.ts.domain.Acesso;

@Repository
public class AcessoDaoImpl extends AbstractDao<Acesso, Long> implements AcessoDao {

	@Override
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

}
