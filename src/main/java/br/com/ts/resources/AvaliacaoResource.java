package br.com.ts.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ts.domain.Avaliacao;
import br.com.ts.domain.Usuario;
import br.com.ts.dto.AvaliacaoDTO;
import br.com.ts.dto.AvaliacaoResultDTO;
import br.com.ts.dto.QuantidadeDTO;
import br.com.ts.service.AvaliacaoService;

@CrossOrigin()
@RestController
@RequestMapping(value="/avaliacoes", produces=MediaType.APPLICATION_JSON_VALUE)
public class AvaliacaoController {
	
	@Autowired
	private AvaliacaoService avaliacaoService;
	
	@PostMapping(value="/add")
    public ResponseEntity<Void> adiciona(@RequestBody AvaliacaoDTO formAvaliacao){
		avaliacaoService.salva(formAvaliacao);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
	
	@PostMapping(value="/add-respostas")
    public ResponseEntity<Void> adicionaResposta(@RequestBody AvaliacaoResultDTO formAvaliacaoResult){
		avaliacaoService.salvaRespostas(formAvaliacaoResult);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
	
	@PutMapping(value="/aceita")
    public ResponseEntity<Void> aceitaAvaliacao(@RequestBody AvaliacaoDTO formAvaliacao){
      	avaliacaoService.atualiza(formAvaliacao, "A");
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
	
	@PutMapping(value="/recusa")
    public ResponseEntity<Void> recusaAvaliacao(@RequestBody AvaliacaoDTO formAvaliacao){
      	avaliacaoService.atualiza(formAvaliacao, "R");
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
  	
  	@DeleteMapping(value="/remove/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id){
  		//TODO verificar se pode excluir: somente o usuário da avaliacao e somente se estiver pendente
      	avaliacaoService.exclui(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
  	
  	@GetMapping(value="/recebidas/pendentes")
	public ResponseEntity<List<AvaliacaoDTO>> getAvaliacoesRecebidas(@RequestParam(name="usuario", required=true) Long idUsuario) {
		Usuario usuario = new Usuario();
		usuario.setId(idUsuario);
		
		Avaliacao avaliacao = new Avaliacao();
		avaliacao.setAvaliado(usuario);
		avaliacao.setStatus("P");
		
		List<Avaliacao> listaDeAvaliacoes = avaliacaoService.listaPorAvaliadoEStatus(avaliacao);
		List<AvaliacaoDTO> listaFA = converteParaFormAvaliacao(listaDeAvaliacoes);
		return new ResponseEntity<List<AvaliacaoDTO>>(listaFA, HttpStatus.OK);
	}
  	
  	@GetMapping(value="/recebidas")
  	public ResponseEntity<List<AvaliacaoDTO>> getAvaliacoes(@RequestParam(name="usuario", required=true) Long idUsuario,
															 @RequestParam(name="status", required=false) String status) {
		Usuario usuario = new Usuario();
		usuario.setId(idUsuario);
		
		Avaliacao avaliacao = new Avaliacao();
		avaliacao.setAvaliado(usuario);
		avaliacao.setStatus(status);
		
		List<Avaliacao> listaDeAvaliacoes = avaliacaoService.listaPorAvaliadoEStatus(avaliacao);
		List<AvaliacaoDTO> listaFA = converteParaFormAvaliacao(listaDeAvaliacoes);
		return new ResponseEntity<List<AvaliacaoDTO>>(listaFA, HttpStatus.OK);
	}
  	
  	@GetMapping(value="/recebidas/pendentes/qtd/{idUsuario}")
	public ResponseEntity<QuantidadeDTO> countAvaliacoesDoAvaliadoEPendentes(@PathVariable("idUsuario") Long idUsuario) {
		Usuario usuario = new Usuario();
		usuario.setId(idUsuario);
		
		Avaliacao avaliacao = new Avaliacao();
		avaliacao.setAvaliado(usuario);
		
		int quantidade = avaliacaoService.countPorAvaliadoEPendente(avaliacao);
		QuantidadeDTO q = new QuantidadeDTO(quantidade);
		return new ResponseEntity<QuantidadeDTO>(q, HttpStatus.OK);
	}
    
    private List<AvaliacaoDTO> converteParaFormAvaliacao(List<Avaliacao> listaDeAvaliacoes) {
		List<AvaliacaoDTO> listaFA = new ArrayList<AvaliacaoDTO>();
		for(Avaliacao a: listaDeAvaliacoes) {
			AvaliacaoDTO fa = new AvaliacaoDTO();
			fa.setId(a.getId());
			fa.setIdUsuario(a.getAvaliador().getId());
			fa.setIdAvaliado(a.getAvaliado().getId());
			fa.setData(a.getData());
			fa.setStatus(a.getStatus());
			
			fa.setNomeUsuario(a.getAvaliador().getNome());
			fa.setNomeAvaliado(a.getAvaliado().getNome());
			
			listaFA.add(fa);
		}
		return listaFA;
	}

}
