package br.com.ts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ts.dao.EstatisticaDao;
import br.com.ts.domain.Estatistica;
import br.com.ts.domain.TipoEstatistica;
import br.com.ts.domain.Usuario;

@Service 
@Transactional(readOnly = false)
public class EstatisticaService {

	@Autowired
	private EstatisticaDao estatisticaDao;
	
	@Autowired
	private TipoEstatisticaService tipoEstatisticaService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	public void salva(Estatistica estatistica) {
		
		List<Estatistica> listaDeEstatistica = null; //TODO estatisticaDao.buscaPorUsuarioAnoTipoEResposta(estatistica.getUsuario().getId(), estatistica.getAno(), estatistica.getIdTipoEstatistica(), estatistica.getIdTipoResposta());
		
		if (listaDeEstatistica != null && listaDeEstatistica.size() > 1) {
			System.out.println("Erro na gravação da estatística... Verificar!!!");
			
		}else if (listaDeEstatistica.size() == 1) {
			somarMaisUm(listaDeEstatistica.get(0), estatistica.getQuantidade());
			
		}else {
			estatisticaDao.save(estatistica);
		}
	}

	private void somarMaisUm(Estatistica e, int quantidade) {
		e.setQuantidade(e.getQuantidade() + quantidade);
	}

	public int buscaEstatistica(Long idUsuario, String tipoDaEstatistica) {
		
		Usuario usuario = usuarioService.find(idUsuario);
		
		TipoEstatistica te = tipoEstatisticaService.buscaPorNome(tipoDaEstatistica);
		
		Estatistica estatistica = new Estatistica();
		estatistica.setUsuario(usuario);
		estatistica.setAno(0);
		estatistica.setIdTipoEstatistica(te.getId());
		
		List<Estatistica> listaDeEstatistica = null; //TODO estatisticaDao.buscaPorUsuarioAnoTipoEResposta(estatistica.getUsuario().getId(), estatistica.getAno(), estatistica.getIdTipoEstatistica(), 0);
		
		int quantidade = 0;
		if (listaDeEstatistica != null && listaDeEstatistica.size() > 0) {
			quantidade = listaDeEstatistica.get(0).getQuantidade();
		}else {
			System.out.println("Não há tipo de avaliação cadastrada.");
		}
		return quantidade;
	}
	
	public int buscaEstatistica(Long idUsuario, long idTipoEstatistica, long idTipoResposta) {
		
		Usuario usuario = usuarioService.find(idUsuario);
		
		Estatistica estatistica = new Estatistica();
		estatistica.setUsuario(usuario);
		estatistica.setAno(0);
		estatistica.setIdTipoEstatistica(idTipoEstatistica);
		estatistica.setIdTipoResposta(idTipoResposta);
		
		List<Estatistica> listaDeEstatistica = null; //TODO estatisticaDao.buscaPorUsuarioAnoTipoEResposta(estatistica.getUsuario().getId(), estatistica.getAno(), estatistica.getIdTipoEstatistica(), estatistica.getIdTipoResposta());
		
		int quantidade = 0;
		if (listaDeEstatistica != null && listaDeEstatistica.size() > 0) {
			quantidade = listaDeEstatistica.get(0).getQuantidade();
		}else {
			System.out.println("Não há tipo de avaliação cadastrada.");
		}
		return quantidade;
	}

}
