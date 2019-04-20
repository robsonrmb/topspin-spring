package br.com.ts.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ts.bean.FormJogo;
import br.com.ts.dao.JogoDao;
import br.com.ts.domain.Estatistica;
import br.com.ts.domain.Jogo;
import br.com.ts.domain.TipoEstatistica;
import br.com.ts.domain.Usuario;

@Service 
@Transactional(readOnly = false)
public class JogoServiceImpl {

	@Autowired
	private JogoDao jogoDao;

	@Autowired
	private EstatisticaServiceImpl estatisticaService;
	
	@Autowired
	private TipoEstatisticaServiceImpl tipoEstatisticaService;
	
	public Jogo salva(FormJogo formJogo) {
		
		Usuario usuario = new Usuario();
		usuario.setId(formJogo.getIdUsuario());
		
		Jogo jogo = new Jogo();
		jogo.setUsuario(usuario);
		jogo.setData(formJogo.getData());
		jogo.setTipo(formJogo.getTipo());
		jogo.setResultado(formJogo.getResultado());
		jogo.setPlacar(formJogo.getPlacar());
		jogo.setQtdTieVencidos(formJogo.getQtdTieVencidos());
		jogo.setQtdTiePerdidos(formJogo.getQtdTiePerdidos());
		
		jogoDao.save(jogo);
		
		//GRAVANDO AS ESTATÍSTICAS
		Calendar dataAtual = Calendar.getInstance();
		
		//VITORIA OU DERROTA
		gravaEstatisticaVitoriaOuDerrota(formJogo, usuario, dataAtual.get(Calendar.YEAR));
		
		//TIE BREAK VENCIDO
		gravaEstatisticaTieBreaksVencidos(formJogo, usuario, dataAtual.get(Calendar.YEAR));
		
		//TIE BREAK PERDIDO
		gravaEstatisticaTieBreaksPerdidos(formJogo, usuario, dataAtual.get(Calendar.YEAR));
		
		return jogo;
	}

	private void gravaEstatisticaVitoriaOuDerrota(FormJogo formJogo, Usuario usuario, int ano) {
		Estatistica estatisticaVD = new Estatistica();
		estatisticaVD.setUsuario(usuario);
		estatisticaVD.setAno(ano);
		
		TipoEstatistica te = tipoEstatisticaService.buscaPorNome(formJogo.getResultadoFormatado()); //VITORIA / DERROTA
		estatisticaVD.setIdTipoEstatistica(te.getId());
		estatisticaVD.setIdTipoResposta(0);
		estatisticaVD.setQuantidade(1);
		estatisticaService.salva(estatisticaVD);
	}

	private void gravaEstatisticaTieBreaksVencidos(FormJogo formJogo, Usuario usuario, int ano) {
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

	private void gravaEstatisticaTieBreaksPerdidos(FormJogo formJogo, Usuario usuario, int ano) {
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

	public void atualiza(Jogo jogo) {
		jogoDao.update(jogo);
	}
	
	public void exclui(Long id) {
		jogoDao.delete(id);
	}

	@Transactional(readOnly = true)
	public Jogo buscaPorId(Long id) {
		return jogoDao.findById(id);
	}

	public List<Jogo> listaTodos() {
		return jogoDao.findAll();
	}

	@Transactional(readOnly = true)
	public List<Jogo> listaPorUsuario(Long id) {
		return jogoDao.listaPorUsuario(id);
	}

	public FormJogo buscaUltimosJogosPorUsuario(Long id, int qtd) {
		List<Jogo> lista = jogoDao.listaUltimosJogosPorUsuario(id, qtd);
		String ultimosJogos = "";
		for(Jogo jogo: lista) {
			ultimosJogos = ultimosJogos + jogo.getResultado();
		}
		FormJogo fj = new FormJogo();
		fj.setIdUsuario(id);
		fj.setUltimosJogos(ultimosJogos);
		return fj;
	}

}
