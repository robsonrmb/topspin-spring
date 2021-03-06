package br.com.ts.resources;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ts.domain.TipoEstatistica;
import br.com.ts.domain.TipoRespostaEstatistica;
import br.com.ts.dto.RespQuantidadeDTO;
import br.com.ts.service.ContabilizacaoService;
import br.com.ts.service.EstatisticaService;
import br.com.ts.service.TipoEstatisticaService;
import br.com.ts.service.exception.RegraNegocioException;

@CrossOrigin()
@RestController
@RequestMapping(value="/estatisticas", produces=MediaType.APPLICATION_JSON_VALUE)
public class EstatisticaResource {
	
	@Autowired
	private EstatisticaService estatisticaService;
	
	@Autowired
	private TipoEstatisticaService tipoEstatisticaService;
	
	@Autowired
	private ContabilizacaoService contabilizacaoService;
	
	@GetMapping(value="/vitoriasederrotas/usuario/{id}")
	public ResponseEntity<RespQuantidadeDTO> getQuantidadeDeVitorias(@PathVariable("id") Long id) {
		
		int qtdVitoria = estatisticaService.buscaEstatistica(id, "VITORIA");
		int qtdDerrota = estatisticaService.buscaEstatistica(id, "DERROTA");
		
		RespQuantidadeDTO frq = new RespQuantidadeDTO();
		frq.setValor1(qtdVitoria);
		frq.setValor2(qtdDerrota);
		
		return new ResponseEntity<RespQuantidadeDTO>(frq, HttpStatus.OK);
	}
	
	@GetMapping(value="/tiebreaks/usuario/{id}")
	public ResponseEntity<RespQuantidadeDTO> getQuantidadeDeTieBreaksVencidos(@PathVariable("id") Long id) {
		
		int qtdVencidos = estatisticaService.buscaEstatistica(id, "TIEBREAKVENCIDO");
		int qtdPerdidos = estatisticaService.buscaEstatistica(id, "TIEBREAKPERDIDO");
		
		RespQuantidadeDTO frq = new RespQuantidadeDTO();
		frq.setValor1(qtdVencidos);
		frq.setValor2(qtdPerdidos);
		
		return new ResponseEntity<RespQuantidadeDTO>(frq, HttpStatus.OK);
	}
	
	@GetMapping(value="/visualiza-estatisticas/usuario/{id}")
	public ResponseEntity<Boolean> getQuantidadeDeEstatisticas(@PathVariable("id") Long id) {
		
		int qtdAvaliacoesAceita = contabilizacaoService.countContabilizacaoGeralDeAvaliacoesAceitasPorUsuario(id);
		
		if (qtdAvaliacoesAceita < 3) {
			throw new RegraNegocioException("Para começar a visualizar suas estatísticas técnicas e táticas, você deve possuir pelo menos 3 avaliações aceitas.");
		}
		
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	@GetMapping(value="/usuario/{id}/tipoEstatistica/{tipo}")
	public ResponseEntity<RespQuantidadeDTO> getQuantidadeDeEstatisticasPorTipo(@PathVariable("id") Long id,
																   				 @PathVariable("tipo") String tipo) {
		long valores[] = new long[12];
		
		TipoEstatistica te = tipoEstatisticaService.buscaPorNome(tipo);
		Iterator<TipoRespostaEstatistica> i = te.getTipoRespostas().iterator();
		int contador = 0;
		while (i.hasNext()) {
			long idTipoResposta = i.next().getId();
			
			valores[contador] = estatisticaService.buscaEstatistica(id, te.getId(), idTipoResposta);
			contador++;
		}
		
		RespQuantidadeDTO frq = new RespQuantidadeDTO();
		frq.setValor1(Integer.parseInt(valores[0]+""));
		frq.setValor2(Integer.parseInt(valores[1]+""));
		frq.setValor3(Integer.parseInt(valores[2]+""));
		frq.setValor4(Integer.parseInt(valores[3]+""));
		
		return new ResponseEntity<RespQuantidadeDTO>(frq, HttpStatus.OK);
	}
	
	@GetMapping(value="/qtd-avaliacoes-aceitas/usuario/{id}")
	public ResponseEntity<RespQuantidadeDTO> getQuantidadeDeAvaliacoesAceitas(@PathVariable("id") Long id) {
		
		int qtdAvaliacoesAceitas = contabilizacaoService.countContabilizacaoGeralDeAvaliacoesAceitasPorUsuario(id);
		
		RespQuantidadeDTO frq = new RespQuantidadeDTO();
		frq.setValor1(qtdAvaliacoesAceitas);
		
		return new ResponseEntity<RespQuantidadeDTO>(frq, HttpStatus.OK);
	}
	
	@GetMapping(value="/qtd-avaliacoes-recusadas/usuario/{id}")
	public ResponseEntity<RespQuantidadeDTO> getQuantidadeDeAvaliacoesRecusadas(@PathVariable("id") Long id) {
		
		int qtdAvaliacoesRecusadas = contabilizacaoService.countContabilizacaoGeralDeAvaliacoesRecusadasPorUsuario(id);
		
		RespQuantidadeDTO frq = new RespQuantidadeDTO();
		frq.setValor1(qtdAvaliacoesRecusadas);
		
		return new ResponseEntity<RespQuantidadeDTO>(frq, HttpStatus.OK);
	}
	
	@GetMapping(value="/qtd-convites-recebidos-aceitos/usuario/{id}")
	public ResponseEntity<RespQuantidadeDTO> getQuantidadeDeConvitesRecebidosAceitos(@PathVariable("id") Long id) {
		
		int qtdConvitesRecebidosAceitos = contabilizacaoService.countContabilizacaoGeralDeConvitesRecebidosAceitosPorUsuario(id);
		
		RespQuantidadeDTO frq = new RespQuantidadeDTO();
		frq.setValor1(qtdConvitesRecebidosAceitos);
		
		return new ResponseEntity<RespQuantidadeDTO>(frq, HttpStatus.OK);
	}
	
	@GetMapping(value="/qtd-convites-recebidos-recusados/usuario/{id}")
	public ResponseEntity<RespQuantidadeDTO> getQuantidadeDeConvitesRecebidosRecusados(@PathVariable("id") Long id) {
		
		int qtdConvitesRecebidosRecusados = contabilizacaoService.countContabilizacaoGeralDeConvitesRecebidosRecusadosPorUsuario(id);
		
		RespQuantidadeDTO frq = new RespQuantidadeDTO();
		frq.setValor1(qtdConvitesRecebidosRecusados);
		
		return new ResponseEntity<RespQuantidadeDTO>(frq, HttpStatus.OK);
	}
	
	@GetMapping(value="/qtd-convites-enviados/usuario/{id}")
	public ResponseEntity<RespQuantidadeDTO> getQuantidadeDeConvitesEnviados(@PathVariable("id") Long id) {
		
		int qtdConvitesEnviados = contabilizacaoService.countContabilizacaoGeralDeConvitesEnviadosPorUsuario(id);
		
		RespQuantidadeDTO frq = new RespQuantidadeDTO();
		frq.setValor1(qtdConvitesEnviados);
		
		return new ResponseEntity<RespQuantidadeDTO>(frq, HttpStatus.OK);
	}
	
	@GetMapping(value="/qtd-jogos-realizados/usuario/{id}")
	public ResponseEntity<RespQuantidadeDTO> getQuantidadeDeJogosRealizados(@PathVariable("id") Long id) {
		
		int qtdJogosRealizados = contabilizacaoService.countContabilizacaoGeralDeJogosRealizadosPorUsuario(id);
		
		RespQuantidadeDTO frq = new RespQuantidadeDTO();
		frq.setValor1(qtdJogosRealizados);
		
		return new ResponseEntity<RespQuantidadeDTO>(frq, HttpStatus.OK);
	}
	
}
