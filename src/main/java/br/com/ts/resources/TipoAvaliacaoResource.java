package br.com.ts.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ts.domain.TipoAvaliacao;
import br.com.ts.service.TipoAvaliacaoService;
import br.com.ts.service.exception.ObjectNotFoundException;

@CrossOrigin()
@RestController
@RequestMapping(value="/tipoavaliacoes", produces=MediaType.APPLICATION_JSON_VALUE)
public class TipoAvaliacaoResource {
	
	@Autowired
	private TipoAvaliacaoService tipoAvaliacaoService;

	@GetMapping(value="/{id}")
	public ResponseEntity<TipoAvaliacao> getUsuario(@PathVariable("id") Long id) {
		TipoAvaliacao tipoAvaliacao = tipoAvaliacaoService.findById(id);
		return ResponseEntity.ok().body(tipoAvaliacao);
	}
	
	@GetMapping
	public ResponseEntity<List<TipoAvaliacao>> getTipoAvaliacaoPorNome(@RequestParam(value="nome", defaultValue="") String nome) {
		
		List<TipoAvaliacao> listaAvaliacoes = new ArrayList<>();
		if ("".equals(nome)) {			
			listaAvaliacoes = tipoAvaliacaoService.findAll();
		}else {
			TipoAvaliacao ta = tipoAvaliacaoService.buscaPorNome(nome.toUpperCase());
			if (ta != null) {
				listaAvaliacoes.add(ta);
			}
		}
		if (listaAvaliacoes == null || listaAvaliacoes.isEmpty()) {
			throw new ObjectNotFoundException("Tipo de avaliação não encontrada.");
		}
		return ResponseEntity.ok().body(listaAvaliacoes);
	}
	
	@GetMapping(value="/find")
	public ResponseEntity<List<TipoAvaliacao>> getTipoAvaliacaoFindByNome(@RequestParam(value="nome", defaultValue="") String nome) {
		
		List<TipoAvaliacao> listaAvaliacoes = new ArrayList<>();
		if ("".equals(nome)) {			
			listaAvaliacoes = tipoAvaliacaoService.findAll();
		}else {
			TipoAvaliacao ta = tipoAvaliacaoService.findByNome(nome.toUpperCase());
			listaAvaliacoes.add(ta);
		}
		return ResponseEntity.ok().body(listaAvaliacoes);
	}
	
}
