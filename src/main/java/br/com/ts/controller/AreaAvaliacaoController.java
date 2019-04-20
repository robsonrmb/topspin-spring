package br.com.ts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ts.domain.AreaAvaliacao;
import br.com.ts.service.AreaAvaliacaoServiceImpl;

@CrossOrigin()
@RestController
@RequestMapping(value="/area-avaliacoes", produces=MediaType.APPLICATION_JSON_VALUE)
public class AreaAvaliacaoController {
	
	@Autowired
	private AreaAvaliacaoServiceImpl areaAvaliacaoService;
	
	@GetMapping(value="/ativas")
	public ResponseEntity<List<AreaAvaliacao>> getAreaAvaliacoes() {
		List<AreaAvaliacao> listaDeAreasAvaliacoes = areaAvaliacaoService.listaAreasAvaliacaoCompleto();
		return new ResponseEntity<List<AreaAvaliacao>>(listaDeAreasAvaliacoes, HttpStatus.OK);
	}

}
