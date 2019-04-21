package br.com.ts.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.ts.domain.Jogo;
import br.com.ts.dto.JogoDTO;
import br.com.ts.service.JogoService;

@CrossOrigin()
@RestController
@RequestMapping(value="/jogos", produces=MediaType.APPLICATION_JSON_VALUE)
public class JogoResource {
	
	@Autowired
	private JogoService jogoService;
	
	@PostMapping(value="/add")
    public ResponseEntity<Jogo> adiciona(@RequestBody JogoDTO formJogo){
		Jogo jogo = jogoService.insert(formJogo);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(jogo.getId()).toUri();
		return ResponseEntity.created(uri).body(jogo);
		
    }
	
	@GetMapping(value="/ultimos-jogos")
	public ResponseEntity<JogoDTO> getUltimosJogosDoUsuario(@RequestParam(name="usuario", required=true) Long idUsuario,
															 @RequestParam(name="qtd", required=true) int qtd) {
		
		if (qtd == 0) {
			qtd = 5;
		}
		JogoDTO fj = jogoService.buscaUltimosJogosPorUsuario(idUsuario, qtd);
		return new ResponseEntity<JogoDTO>(fj, HttpStatus.OK);	
	}
	
	@GetMapping(value="/usuario/{id}")
	public ResponseEntity<List<JogoDTO>> getJogosDoUsuario(@PathVariable("id") Long id) {
		List<Jogo> listaDeJogos = jogoService.listaPorUsuario(id);
		List<JogoDTO> lista = converteJogosParaFormJogo(listaDeJogos);
		return new ResponseEntity<List<JogoDTO>>(lista, HttpStatus.OK);	
	}

	private List<JogoDTO> converteJogosParaFormJogo(List<Jogo> listaDeJogos) {
		List<JogoDTO> lista_fj = new ArrayList<JogoDTO>();
		for (Jogo jogo: listaDeJogos) {
			JogoDTO fj = new JogoDTO();
			fj.setId(jogo.getId());
			fj.setIdUsuario(jogo.getUsuario().getId());
			
			//No cadastro do jogo não está sendo informado o adversário.
			//fj.setIdAdversario(jogo.getAdversario().getId());
			
			fj.setData(jogo.getData());
			fj.setTipo(jogo.getTipo());
			fj.setResultado(jogo.getResultado());
			fj.setPlacar(jogo.getPlacar());
			fj.setQtdTieVencidos(jogo.getQtdTieVencidos());
			fj.setQtdTiePerdidos(jogo.getQtdTiePerdidos());
			fj.setDataJogoFormatada(jogo.getDataJogoFormatada());
			
			lista_fj.add(fj);
		}
		return lista_fj;
	}

}
