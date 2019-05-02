package br.com.ts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ts.dao.TipoRespostaEstatisticaDao;
import br.com.ts.domain.TipoRespostaEstatistica;

@Service 
@Transactional(readOnly = false)
public class TipoRespostaEstatisticaService {

	@Autowired
	private TipoRespostaEstatisticaDao tipoRespostaEstatisticaDao;
	
	@Transactional(readOnly = true)
	public TipoRespostaEstatistica buscaPorNome(String nome) {
		return tipoRespostaEstatisticaDao.buscaPorNome(nome);
	}

	public TipoRespostaEstatistica findById(long id) {
		return tipoRespostaEstatisticaDao.findById(id).get();
	}

}
