package br.com.ts.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ts.dao.AreaAvaliacaoDao;
import br.com.ts.domain.AreaAvaliacao;
import br.com.ts.domain.TipoAvaliacao;

@Service 
@Transactional(readOnly = false)
public class AreaAvaliacaoService {

	@Autowired
	private AreaAvaliacaoDao areaAvaliacaoDao;

	public List<AreaAvaliacao> listaAreasAvaliacaoCompleto() {
		ArrayList<AreaAvaliacao> listaDeAreasAvaliacoes = null; //TODO (ArrayList<AreaAvaliacao>) areaAvaliacaoDao.listaAreasAvaliacaoCompleto();
		for (AreaAvaliacao aa: listaDeAreasAvaliacoes) {
			for (TipoAvaliacao ta: aa.getTipo()) {
				ta.getTipoRespostas().size();
			}
		}
		return listaDeAreasAvaliacoes;
	}
	
}
