package br.com.ts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ts.dao.dinamic.ContabilizacaoDaoImpl;
import br.com.ts.dao.dinamic.ConviteDaoImpl;
import br.com.ts.dao.dinamic.JogoDaoImpl;
import br.com.ts.domain.Contabilizacao;
import br.com.ts.domain.Convite;
import br.com.ts.domain.Jogo;
import br.com.ts.domain.Usuario;

@Service 
@Transactional(readOnly = false)
public class ContabilizacaoService {

	@Autowired
	private ContabilizacaoDaoImpl contabilizacaoDaoImpl;
	
	@Autowired
	private ConviteDaoImpl conviteDaoImpl;
	
	@Autowired
	private JogoDaoImpl jogoDaoImpl;
	
	public void salva(Contabilizacao contabilizacao, String tipo) {
		
		List<Contabilizacao> listaDeContabilizacao = contabilizacaoDaoImpl.buscaPorUsuarioAno(contabilizacao.getUsuario().getId(), contabilizacao.getAno());
		
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
			contabilizacaoDaoImpl.save(contabilizacao);
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
		
		List<Contabilizacao> listaDeContabilizacao = contabilizacaoDaoImpl.buscaPorUsuarioAno(idUsuario, 0);
		
		int contador = 0;
		for (Contabilizacao c: listaDeContabilizacao) {
			contador = contador + c.getQuantidadeAvaliacaoAceita();
		}
		return contador;
	}
	
	public int countContabilizacaoGeralDeAvaliacoesRecusadasPorUsuario(Long idUsuario) {
		
		List<Contabilizacao> listaDeContabilizacao = contabilizacaoDaoImpl.buscaPorUsuarioAno(idUsuario, 0);
		
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
		int qtd = conviteDaoImpl.countPorConvidadoEStatus(convite);
		return qtd;
	}

	public int countContabilizacaoGeralDeConvitesRecebidosRecusadosPorUsuario(Long id) {
		Usuario usuario = new Usuario();
		usuario.setId(id);
		
		Convite convite = new Convite();
		convite.setConvidado(usuario);
		convite.setStatus("R");
		int qtd = conviteDaoImpl.countPorConvidadoEStatus(convite);
		return qtd;
	}

	public int countContabilizacaoGeralDeConvitesEnviadosPorUsuario(Long id) {
		Usuario usuario = new Usuario();
		usuario.setId(id);
		
		Convite convite = new Convite();
		convite.setUsuario(usuario);
		int qtd = conviteDaoImpl.countConvitesEnviadosPorUsuario(convite);
		return qtd;
	}

	public int countContabilizacaoGeralDeJogosRealizadosPorUsuario(Long id) {
		Usuario usuario = new Usuario();
		usuario.setId(id);
		
		Jogo jogo = new Jogo();
		jogo.setUsuario(usuario);
		int qtd = jogoDaoImpl.countJogosRealizadoPorUsuario(jogo);
		return qtd;
	}

}
