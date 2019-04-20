package br.com.ts.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.ts.domain.Convite;

@Repository
public class ConviteDaoImpl extends AbstractDao<Convite, Long> implements ConviteDao {

	@Override
	public List<Convite> listaPorUsuarioEStatus(Convite convite) {
		String query = "SELECT c FROM Convite c where 1=1";
		if (convite.getUsuario() != null && convite.getUsuario().getId() != 0) {
			query = query + " and c.usuario.id = :usuario";
		}
		if (convite.getStatus() != null) {
			query = query + " and c.status = :status";
		}
		TypedQuery<Convite> q = getEntityManager().createQuery(query, Convite.class); 
		if (convite.getUsuario() != null && convite.getUsuario().getId() != 0) {
			q.setParameter("usuario", convite.getUsuario().getId());
		}
		if (convite.getStatus() != null) {
			q.setParameter("status", convite.getStatus());
		}
		return q.getResultList();
	}
	
	@Override
	public List<Convite> listaPorConvidadoEStatus(Convite convite) {
		String query = "SELECT c FROM Convite c where 1=1";
		if (convite.getConvidado() != null && convite.getConvidado().getId() != 0) {
			query = query + " and c.convidado.id = :convidado";
		}
		if (convite.getStatus() != null) {
			query = query + " and c.status = :status";
		}
		TypedQuery<Convite> q = getEntityManager().createQuery(query, Convite.class); 
		if (convite.getConvidado() != null && convite.getConvidado().getId() != 0) {
			q.setParameter("convidado", convite.getConvidado().getId());
		}
		if (convite.getStatus() != null) {
			q.setParameter("status", convite.getStatus());
		}
		return q.getResultList();
	}

	@Override
	public List<Convite> listaPorConvidadoENaoPendentes(Convite convite) {
		String query = "SELECT c FROM Convite c where 1=1 and c.status != 'P'";
		if (convite.getConvidado() != null && convite.getConvidado().getId() != 0) {
			query = query + " and c.convidado.id = :convidado";
		}
		TypedQuery<Convite> q = getEntityManager().createQuery(query, Convite.class); 
		if (convite.getConvidado() != null && convite.getConvidado().getId() != 0) {
			q.setParameter("convidado", convite.getConvidado().getId());
		}
		return q.getResultList();
	}
	
	@Override
	public int countPorConvidadoEStatus(Convite convite) {
		String query = "SELECT count(c) FROM Convite c where 1=1";
		if (convite.getConvidado() != null && convite.getConvidado().getId() != 0) {
			query = query + " and c.convidado.id = :convidado";
		}
		if (convite.getStatus() != null) {
			query = query + " and c.status = :status";
		}
		TypedQuery<Long> q = getEntityManager().createQuery(query, Long.class); 
		if (convite.getConvidado() != null && convite.getConvidado().getId() != 0) {
			q.setParameter("convidado", convite.getConvidado().getId());
		}
		if (convite.getStatus() != null) {
			q.setParameter("status", convite.getStatus());
		}
		long valor = q.getSingleResult();
		return Integer.parseInt(valor+"");
	}
	
	@Override
	public int countConvitesEnviadosPorUsuario(Convite convite) {
		String query = "SELECT count(c) FROM Convite c where 1=1";
		if (convite.getUsuario() != null && convite.getUsuario().getId() != 0) {
			query = query + " and c.usuario.id = :usuario";
		}
		TypedQuery<Long> q = getEntityManager().createQuery(query, Long.class); 
		if (convite.getUsuario() != null && convite.getUsuario().getId() != 0) {
			q.setParameter("usuario", convite.getUsuario().getId());
		}
		long valor = q.getSingleResult();
		return Integer.parseInt(valor+"");
	}

}
