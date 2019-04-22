package br.com.ts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ts.dao.TipoEstatisticaDao;
import br.com.ts.domain.TipoEstatistica;

@Service 
@Transactional(readOnly = false)
public class TipoEstatisticaService {

	@Autowired
	private TipoEstatisticaDao tipoEstatisticaDao;
	
	public TipoEstatistica findById(Long id) {
		return tipoEstatisticaDao.findById(id).get();
	}
	
	public List<TipoEstatistica> findAll() {
		return tipoEstatisticaDao.findAll();
	}
	
	public TipoEstatistica findByNome(String nome) {
		return tipoEstatisticaDao.findByNome(nome);
	}
	
	public TipoEstatistica buscaPorNome(String nome) {
		return tipoEstatisticaDao.buscaPorNome(nome);
	}

}
