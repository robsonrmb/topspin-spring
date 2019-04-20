package br.com.ts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ts.dao.ContabilizacaoDao;
import br.com.ts.dao.ConviteDao;
import br.com.ts.dao.JogoDao;
import br.com.ts.domain.Contabilizacao;
import br.com.ts.domain.Convite;
import br.com.ts.domain.Jogo;
import br.com.ts.domain.Usuario;

@Service @Transactional(readOnly = false)
public class ContabilizacaoServiceImpl {

	@Autowired
	private ContabilizacaoDao contabilizacaoDao;
	
	@Autowired
	private ConviteDao conviteDao;
	
	@Autowired
	private JogoDao jogoDao;
	
	public void salva(Contabilizacao contabilizacao, String tipo) {
		
		List<Contabilizacao> listaDeContabilizacao = contabilizacaoDao
			.buscaPorUsuarioAno(contabilizacao.getUsuario().getId(), contabilizacao.getAno());
		
		if (listaDeContabilizacao != null && listaDeContabilizacao.size() > 1) {
			System.out.println("Erro na gravação da contabilizacao... Verificar!!!");
			
		}else if (listaDeContabilizacao.size() == 1) {
			somarMaisUm(listaDeContabilizacao.get(0), tipo);
			
		}else {
			if ("A".equals(tipo)) {
				contabilizacao.setQuantidadeAvaliacaoAceita(1);
			}else {
				contabilizacao.setQuantidadeAvaliacaoRecusada(1);
			}
			contabilizacaoDao.save(contabilizacao);
		}
	}

	private void somarMaisUm(Contabilizacao c, String tipo) {
		if ("A".equals(tipo)) {
			c.setQuantidadeAvaliacaoAceita(c.getQuantidadeAvaliacaoAceita() + 1);
		}else {
			c.setQuantidadeAvaliacaoRecusada(c.getQuantidadeAvaliacaoRecusada() + 1);
		}
	}

	public int countContabilizacaoGeralDeAvaliacoesAceitasPorUsuario(Long idUsuario) {
		
		List<Contabilizacao> listaDeContabilizacao = contabilizacaoDao
				.buscaPorUsuarioAno(idUsuario, 0);
		
		int contador = 0;
		for (Contabilizacao c: listaDeContabilizacao) {
			contador = contador + c.getQuantidadeAvaliacaoAceita();
		}
		return contador;
	}
	
	public int countContabilizacaoGeralDeAvaliacoesRecusadasPorUsuario(Long idUsuario) {
		
		List<Contabilizacao> listaDeContabilizacao = contabilizacaoDao
				.buscaPorUsuarioAno(idUsuario, 0);
		
		int contador = 0;
		for (Contabilizacao c: listaDeContabilizacao) {
			contador = contador + c.getQuantidadeAvaliacaoRecusada();
		}
		return contador;
	}

	public int countContabilizacaoGeralDeConvitesRecebidosAceitosPorUsuario(Long id) {
		Usuario usuario = new Usuario();
		usuario.setId(id);
		
		Convite convite = new Convite();
		convite.setConvidado(usuario);
		convite.setStatus("A");
		int qtd = conviteDao.countPorConvidadoEStatus(convite);
		return qtd;
	}

	public int countContabilizacaoGeralDeConvitesRecebidosRecusadosPorUsuario(Long id) {
		Usuario usuario = new Usuario();
		usuario.setId(id);
		
		Convite convite = new Convite();
		convite.setConvidado(usuario);
		convite.setStatus("R");
		int qtd = conviteDao.countPorConvidadoEStatus(convite);
		return qtd;
	}

	public int countContabilizacaoGeralDeConvitesEnviadosPorUsuario(Long id) {
		Usuario usuario = new Usuario();
		usuario.setId(id);
		
		Convite convite = new Convite();
		convite.setUsuario(usuario);
		int qtd = conviteDao.countConvitesEnviadosPorUsuario(convite);
		return qtd;
	}

	public int countContabilizacaoGeralDeJogosRealizadosPorUsuario(Long id) {
		Usuario usuario = new Usuario();
		usuario.setId(id);
		
		Jogo jogo = new Jogo();
		jogo.setUsuario(usuario);
		int qtd = jogoDao.countJogosRealizadoPorUsuario(jogo);
		return qtd;
	}

}
