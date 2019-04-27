package br.com.ts.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ts.dao.AvaliacaoDao;
import br.com.ts.dao.AvaliacaoRespostasDao;
import br.com.ts.dao.TipoAvaliacaoDao;
import br.com.ts.dao.TipoRespostaAvaliacaoDao;
import br.com.ts.dao.UsuarioDao;
import br.com.ts.domain.Avaliacao;
import br.com.ts.domain.AvaliacaoRespostas;
import br.com.ts.domain.Contabilizacao;
import br.com.ts.domain.Estatistica;
import br.com.ts.domain.TipoAvaliacao;
import br.com.ts.domain.TipoEstatistica;
import br.com.ts.domain.TipoRespostaAvaliacao;
import br.com.ts.domain.TipoRespostaEstatistica;
import br.com.ts.domain.Usuario;
import br.com.ts.dto.AvaliacaoDTO;
import br.com.ts.dto.AvaliacaoResultDTO;
import br.com.ts.enums.Perfil;
import br.com.ts.security.UserSS;
import br.com.ts.service.exception.AuthorizationException;

@Service @Transactional(readOnly = false)
public class AvaliacaoService {

	@Autowired
	private AvaliacaoDao avaliacaoDao;
	
	@Autowired
	private TipoAvaliacaoService tipoAvaliacaoService;
	
	@Autowired
	private AvaliacaoRespostasDao avaliacaoRespostasDao;
	
	@Autowired
	private TipoAvaliacaoDao tipoAvaliacaoDao;
	
	@Autowired
	private TipoRespostaAvaliacaoDao tipoRespostaAvaliacaoDao;
	
	@Autowired
	private EstatisticaService estatisticaService;
	
	@Autowired
	private TipoEstatisticaService tipoEstatisticaService;
	
	@Autowired
	private TipoRespostaEstatisticaService tipoRespostaEstatisticaService;
	
	@Autowired
	private UsuarioDao usuarioDao;

	@Autowired
	private ContabilizacaoService contabilizacaoService;

	public void salva(AvaliacaoDTO avaliacaoDTO) {
		
		Usuario usuario = usuarioDao.findById(avaliacaoDTO.getIdUsuario()).get();
		Usuario avaliado = usuarioDao.findById(avaliacaoDTO.getIdAvaliado()).get();
		
		UserSS usuarioLogado = AuthenticationService.getUsuarioAutenticado();
		if (usuarioLogado == null || (usuarioLogado.getEmail().equals(avaliado.getEmail()))) {
			throw new AuthorizationException("Acesso negado!!! Um usuário não pode avaliar a si mesmo.");
		}
		
		Avaliacao avaliacao = new Avaliacao();
		avaliacao.setAvaliador(usuario);
		avaliacao.setAvaliado(avaliado);
		
		avaliacao.setData(new Date());
		avaliacao.setStatus("P");
		avaliacaoDao.save(avaliacao);
		
		TipoAvaliacao tipoAvaliacao = tipoAvaliacaoDao.buscaPorNome("SAQUE");
		TipoRespostaAvaliacao tipoResposta = tipoRespostaAvaliacaoDao.buscaPorNome(avaliacaoDTO.getRespostaSaque().toUpperCase());
		AvaliacaoRespostas ar = new AvaliacaoRespostas(avaliacao, tipoAvaliacao.getId(), tipoResposta.getId());
		avaliacaoRespostasDao.save(ar);
		
		tipoAvaliacao = tipoAvaliacaoDao.buscaPorNome("FOREHAND");
		tipoResposta = tipoRespostaAvaliacaoDao.buscaPorNome(avaliacaoDTO.getRespostaForehand().toUpperCase());
		ar = new AvaliacaoRespostas(avaliacao, tipoAvaliacao.getId(), tipoResposta.getId());
		avaliacaoRespostasDao.save(ar);
		
		tipoAvaliacao = tipoAvaliacaoDao.buscaPorNome("BACKHAND");
		tipoResposta = tipoRespostaAvaliacaoDao.buscaPorNome(avaliacaoDTO.getRespostaBackhand().toUpperCase());
		ar = new AvaliacaoRespostas(avaliacao, tipoAvaliacao.getId(), tipoResposta.getId());
		avaliacaoRespostasDao.save(ar);
		
		tipoAvaliacao = tipoAvaliacaoDao.buscaPorNome("VOLEIO");
		tipoResposta = tipoRespostaAvaliacaoDao.buscaPorNome(avaliacaoDTO.getRespostaVoleio().toUpperCase());
		ar = new AvaliacaoRespostas(avaliacao, tipoAvaliacao.getId(), tipoResposta.getId());
		avaliacaoRespostasDao.save(ar);
		
		tipoAvaliacao = tipoAvaliacaoDao.buscaPorNome("SMASH");
		tipoResposta =tipoRespostaAvaliacaoDao.buscaPorNome(avaliacaoDTO.getRespostaSmash().toUpperCase());
		ar = new AvaliacaoRespostas(avaliacao, tipoAvaliacao.getId(), tipoResposta.getId());
		avaliacaoRespostasDao.save(ar);
		
		tipoAvaliacao = tipoAvaliacaoDao.buscaPorNome("OFENSIVO");
		tipoResposta = tipoRespostaAvaliacaoDao.buscaPorNome(avaliacaoDTO.getRespostaOfensivo().toUpperCase());
		ar = new AvaliacaoRespostas(avaliacao, tipoAvaliacao.getId(), tipoResposta.getId());
		avaliacaoRespostasDao.save(ar);
		
		tipoAvaliacao = tipoAvaliacaoDao.buscaPorNome("DEFENSIVO");
		tipoResposta = tipoRespostaAvaliacaoDao.buscaPorNome(avaliacaoDTO.getRespostaDefensivo().toUpperCase());
		ar = new AvaliacaoRespostas(avaliacao, tipoAvaliacao.getId(), tipoResposta.getId());
		avaliacaoRespostasDao.save(ar);
		
		tipoAvaliacao = tipoAvaliacaoDao.buscaPorNome("TATICO");
		tipoResposta = tipoRespostaAvaliacaoDao.buscaPorNome(avaliacaoDTO.getRespostaTatico().toUpperCase());
		ar = new AvaliacaoRespostas(avaliacao, tipoAvaliacao.getId(), tipoResposta.getId());
		avaliacaoRespostasDao.save(ar);
		
		tipoAvaliacao = tipoAvaliacaoDao.buscaPorNome("COMPETITIVO");
		tipoResposta = tipoRespostaAvaliacaoDao.buscaPorNome(avaliacaoDTO.getRespostaCompetitivo().toUpperCase());
		ar = new AvaliacaoRespostas(avaliacao, tipoAvaliacao.getId(), tipoResposta.getId());
		avaliacaoRespostasDao.save(ar);
		
		tipoAvaliacao = tipoAvaliacaoDao.buscaPorNome("PREPARO");
		tipoResposta = tipoRespostaAvaliacaoDao.buscaPorNome(avaliacaoDTO.getRespostaPreparo().toUpperCase());
		ar = new AvaliacaoRespostas(avaliacao, tipoAvaliacao.getId(), tipoResposta.getId());
		avaliacaoRespostasDao.save(ar);
		
	}

	public void salvaRespostas(AvaliacaoResultDTO formAvaliacaoResult) {
		Usuario usuario = usuarioDao.findById(formAvaliacaoResult.getIdUsuario()).get();
		Usuario avaliado = usuarioDao.findById(formAvaliacaoResult.getIdAvaliado()).get();
		
		Avaliacao avaliacao = new Avaliacao();
		avaliacao.setAvaliador(usuario);
		avaliacao.setAvaliado(avaliado);
		
		avaliacao.setData(new Date());
		avaliacao.setStatus("P");
		avaliacaoDao.save(avaliacao);
		
		for (String str: formAvaliacaoResult.getRespostas()) {
			String resposta[] = new String[2];
			resposta = str.split("#");
			//TipoAvaliacao tipoAvaliacao = tipoAvaliacaoService.findById(Long.parseLong(resposta[0])); //Comentei pq a variável não estava sendo usada.
			TipoRespostaAvaliacao tipoResposta = tipoRespostaAvaliacaoDao.findById(Long.parseLong(resposta[1])).get();
			AvaliacaoRespostas ar = new AvaliacaoRespostas(avaliacao, Long.parseLong(resposta[0]), tipoResposta.getId());
			avaliacaoRespostasDao.save(ar);
		}
	}

	public void atualiza(AvaliacaoDTO formAvaliacao, String status) {
		Avaliacao avaliacao = avaliacaoDao.findById(formAvaliacao.getId()).get();
		avaliacao.setStatus(status);
		
		if (status != null && status.equals("A")) {	
			
			Calendar dataAtual = Calendar.getInstance();
			
			//GRAVAR AS ESTATÍSTICAS
			gravaEstatistica("SAQUE", avaliacao, dataAtual.get(Calendar.YEAR));
			gravaEstatistica("FOREHAND", avaliacao, dataAtual.get(Calendar.YEAR));
			gravaEstatistica("BACKHAND", avaliacao, dataAtual.get(Calendar.YEAR));
			gravaEstatistica("VOLEIO", avaliacao, dataAtual.get(Calendar.YEAR));
			gravaEstatistica("SMASH", avaliacao, dataAtual.get(Calendar.YEAR));
			gravaEstatistica("OFENSIVO", avaliacao, dataAtual.get(Calendar.YEAR));
			gravaEstatistica("DEFENSIVO", avaliacao, dataAtual.get(Calendar.YEAR));
			gravaEstatistica("TATICO", avaliacao, dataAtual.get(Calendar.YEAR));
			gravaEstatistica("COMPETITIVO", avaliacao, dataAtual.get(Calendar.YEAR));
			gravaEstatistica("PREPARO", avaliacao, dataAtual.get(Calendar.YEAR));
			
		}
		//GRAVA A CONTABILIZACAO
		//TODO método depreciado
		int ano = avaliacao.getData().getYear();
		gravaContabilizacao(avaliacao.getAvaliado(), status, ano);
	}

	private void gravaContabilizacao(Usuario avaliado, String status, int ano) {
		Contabilizacao contabilizacao = new Contabilizacao();
		contabilizacao.setUsuario(avaliado);
		contabilizacao.setAno(ano);
		contabilizacaoService.salva(contabilizacao, status);
	}

	private void gravaEstatistica(String nome, Avaliacao avaliacao, int ano) {
		Estatistica estatistica = new Estatistica();
		estatistica.setUsuario(avaliacao.getAvaliado());
		estatistica.setAno(ano);
		
		TipoAvaliacao ta = tipoAvaliacaoDao.buscaPorNome(nome);
		long idAvaliacaoResposta = 0;
		for (AvaliacaoRespostas ar: avaliacao.getRespostas()) {
			if (ar.getIdTipoAvaliacao() == ta.getId()) {
				idAvaliacaoResposta = ar.getIdTipoResposta();
				break;
			}
		}
		TipoRespostaAvaliacao tra = tipoRespostaAvaliacaoDao.findById(idAvaliacaoResposta).get();
		
		TipoEstatistica te = tipoEstatisticaService.buscaPorNome(nome);
		TipoRespostaEstatistica tre = tipoRespostaEstatisticaService.buscaPorNome(tra.getNome());
		
		estatistica.setIdTipoEstatistica(te.getId());
		estatistica.setIdTipoResposta(tre.getId());
		estatistica.setQuantidade(1);
		estatisticaService.salva(estatistica);
	}

	public void exclui(Long id) {
		avaliacaoDao.deleteById(id);
	}

	public List<Avaliacao> listaPorAvaliadoEStatus(Avaliacao avaliacao) {
		return null; //TODO avaliacaoDao.listaPorAvaliadoEStatus(avaliacao);
	}
	
	public int countPorAvaliadoEPendente(Avaliacao avaliacao) {
		return 0; //TODO avaliacaoDao.countPorAvaliadoEPendente(avaliacao);
	}

}
