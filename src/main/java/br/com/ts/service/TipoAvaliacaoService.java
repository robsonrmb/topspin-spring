package br.com.ts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ts.dao.TipoAvaliacaoDao;
import br.com.ts.domain.TipoAvaliacao;

@Service 
@Transactional(readOnly = false)
public class TipoAvaliacaoService {

	@Autowired
	private TipoAvaliacaoDao tipoAvaliacaoDao;
	
	public TipoAvaliacao findById(Long id) {
		return tipoAvaliacaoDao.findById(id).get();
	}
	
	public List<TipoAvaliacao> findAll() {
		return tipoAvaliacaoDao.findAll();
	}
	
	public TipoAvaliacao findByNome(String nome) {
		return tipoAvaliacaoDao.findByNome(nome);
	}
	
	public TipoAvaliacao buscaPorNome(String nome) {
		return tipoAvaliacaoDao.buscaPorNome(nome);
	}

}
