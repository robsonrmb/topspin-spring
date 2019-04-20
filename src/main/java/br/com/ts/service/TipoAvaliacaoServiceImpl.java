package br.com.ts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ts.dao.TipoAvaliacaoDao;
import br.com.ts.domain.TipoAvaliacao;

@Service 
@Transactional(readOnly = false)
public class TipoAvaliacaoServiceImpl {

	@Autowired
	private TipoAvaliacaoDao tipoAvaliacaoDao;
	
	@Transactional(readOnly = true)
	public TipoAvaliacao buscaPorId(Long id) {
		return tipoAvaliacaoDao.findById(id);
	}
	
	@Transactional(readOnly = true)
	public TipoAvaliacao buscaPorNome(String nome) {
		return tipoAvaliacaoDao.buscaPorNome(nome);
	}

}
