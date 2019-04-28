package br.com.ts.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ts.dao.JogoDao;
import br.com.ts.dao.dinamic.JogoDaoImpl;
import br.com.ts.domain.Estatistica;
import br.com.ts.domain.Jogo;
import br.com.ts.domain.TipoEstatistica;
import br.com.ts.domain.Usuario;
import br.com.ts.dto.JogoDTO;

@Service 
@Transactional(readOnly = false)
public class JogoService {

	@Autowired
	private JogoDao jogoDao;
	
	@Autowired
	private JogoDaoImpl jogoDaoImpl;
	
	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private EstatisticaService estatisticaService;
	
	@Autowired
	private TipoEstatisticaService tipoEstatisticaService;
	
	public Jogo insert(JogoDTO jogoDTO) {
		
		Usuario usuario = usuarioService.find(jogoDTO.getIdUsuario());
		
		RegrasNegocioService.umUsuarioSoPodeCadastrarJogoDeleMesmo(usuario);
		
		Jogo jogo = new Jogo(null, jogoDTO.getData(), jogoDTO.getTipo(), jogoDTO.getResultado(), jogoDTO.getPlacar(), jogoDTO.getQtdTieVencidos(), jogoDTO.getQtdTiePerdidos(), usuario, null);
		jogoDao.save(jogo);
		
		//GRAVANDO AS ESTATÍSTICAS
		Calendar dataAtual = Calendar.getInstance();
		
		//VITORIA OU DERROTA
		gravaEstatisticaVitoriaOuDerrota(jogoDTO, usuario, dataAtual.get(Calendar.YEAR));
		
		//TIE BREAK VENCIDO
		gravaEstatisticaTieBreaksVencidos(jogoDTO, usuario, dataAtual.get(Calendar.YEAR));
		
		//TIE BREAK PERDIDO
		gravaEstatisticaTieBreaksPerdidos(jogoDTO, usuario, dataAtual.get(Calendar.YEAR));
		
		return jogo;
	}

	private void gravaEstatisticaVitoriaOuDerrota(JogoDTO jogoDTO, Usuario usuario, int ano) {
		Estatistica estatisticaVD = new Estatistica();
		estatisticaVD.setUsuario(usuario);
		estatisticaVD.setAno(ano);
		
		TipoEstatistica te = tipoEstatisticaService.buscaPorNome(jogoDTO.getResultadoFormatado()); //VITORIA / DERROTA
		estatisticaVD.setIdTipoEstatistica(te.getId());
		estatisticaVD.setIdTipoResposta(0);
		estatisticaVD.setQuantidade(1);
		estatisticaService.salva(estatisticaVD);
	}

	private void gravaEstatisticaTieBreaksVencidos(JogoDTO formJogo, Usuario usuario, int ano) {
		if (formJogo.getQtdTieVencidos() > 0) {
			Estatistica estatisticaTV = new Estatistica();
			estatisticaTV.setUsuario(usuario);
			estatisticaTV.setAno(ano);
			
			TipoEstatistica te = tipoEstatisticaService.buscaPorNome("TIEBREAKVENCIDO"); 
			estatisticaTV.setIdTipoEstatistica(te.getId());
			estatisticaTV.setIdTipoResposta(0);
			estatisticaTV.setQuantidade(formJogo.getQtdTieVencidos());
			estatisticaService.salva(estatisticaTV);
		}
	}

	private void gravaEstatisticaTieBreaksPerdidos(JogoDTO formJogo, Usuario usuario, int ano) {
		if (formJogo.getQtdTiePerdidos() > 0) {
			Estatistica estatisticaTP = new Estatistica();
			estatisticaTP.setUsuario(usuario);
			estatisticaTP.setAno(ano);
			
			TipoEstatistica te = tipoEstatisticaService.buscaPorNome("TIEBREAKPERDIDO"); 
			estatisticaTP.setIdTipoEstatistica(te.getId());
			estatisticaTP.setIdTipoResposta(0);
			estatisticaTP.setQuantidade(formJogo.getQtdTiePerdidos());
			estatisticaService.salva(estatisticaTP);
		}
	}

	public void update(Jogo jogo) {
		jogoDao.save(jogo);
	}
	
	public void delete(Long id) {
		jogoDao.deleteById(id);
	}

	@Transactional(readOnly = true)
	public Jogo findById(Long id) {
		return jogoDao.findById(id).get();
	}

	public List<Jogo> findAll() {
		return jogoDao.findAll();
	}

	@Transactional(readOnly = true)
	public List<Jogo> listaPorUsuario(Long id) {
		return null; //TODO jogoDao.listaPorUsuario(id);
	}

	public JogoDTO buscaUltimosJogosPorUsuario(Long id, int qtd) {
		List<Jogo> lista = jogoDaoImpl.listaUltimosJogosPorUsuario(id, qtd);
		String ultimosJogos = "";
		for(Jogo jogo: lista) {
			ultimosJogos = ultimosJogos + jogo.getResultado();
		}
		JogoDTO fj = new JogoDTO();
		fj.setIdUsuario(id);
		fj.setUltimosJogos(ultimosJogos);
		return fj;
	}

}
