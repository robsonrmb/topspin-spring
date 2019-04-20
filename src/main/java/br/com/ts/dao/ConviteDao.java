package br.com.ts.dao;

import java.util.List;

import br.com.ts.domain.Convite;

public interface ConviteDao {
	
	void save(Convite convite);
	
	void update(Convite convite);
	
	void delete(Long id);
	
	Convite findById(Long id);
	
	List<Convite> findAll();
	
	List<Convite> listaPorUsuarioEStatus(Convite convite);
	
	List<Convite> listaPorConvidadoEStatus(Convite convite);

	List<Convite> listaPorConvidadoENaoPendentes(Convite convite);
	
	int countPorConvidadoEStatus(Convite convite);

	int countConvitesEnviadosPorUsuario(Convite convite);

}
