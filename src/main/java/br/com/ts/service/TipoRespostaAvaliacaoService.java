package br.com.ts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ts.dao.TipoRespostaAvaliacaoDao;
import br.com.ts.domain.TipoRespostaAvaliacao;

@Service 
@Transactional(readOnly = false)
public class TipoRespostaAvaliacaoServiceImpl {

	@Autowired
	private TipoRespostaAvaliacaoDao tipoRespostaAvaliacaoDao;
	
	@Transactional(readOnly = true)
	public TipoRespostaAvaliacao buscaPorNome(String nome) {
		return tipoRespostaAvaliacaoDao.buscaPorNome(nome);
	}

}
