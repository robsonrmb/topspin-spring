package br.com.ts.service;

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
	
	@Transactional(readOnly = true)
	public TipoEstatistica buscaPorNome(String nome) {
		return null; //TODO tipoEstatisticaDao.buscaPorNome(nome);
	}

}
