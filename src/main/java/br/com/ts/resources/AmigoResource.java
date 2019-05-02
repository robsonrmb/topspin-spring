package br.com.ts.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ts.domain.Usuario;
import br.com.ts.dto.UsuarioAmigoDTO;
import br.com.ts.service.AmigoService;

@CrossOrigin
@RestController
@RequestMapping(value="/amigos", produces=MediaType.APPLICATION_JSON_VALUE)
public class AmigoResource {
	
	@Autowired
	private AmigoService amigoService;

	@GetMapping(value="/{id}")
    public ResponseEntity<List<Usuario>> amigosPorUsuario(@PathVariable long id) {
		if (id == 0) {
			//throw new ApiNegocioRuntimeException("Usuário não informado!!!", HttpStatus.BAD_REQUEST, "Informe um usuário para realizar a pesquisa.");
  		}
		List<Usuario> listaAmigos = this.amigoService.listaAmigos(id);
        return new ResponseEntity<List<Usuario>>(listaAmigos, HttpStatus.OK);	
    }
	
	@PostMapping
	public ResponseEntity<?> adiciona(@RequestBody @Valid UsuarioAmigoDTO usuarioAmigoDTO) {
		amigoService.insert(usuarioAmigoDTO);
	    return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
  	
	@PostMapping(value="/remove") //TODO USAR O MÉTODO DELETE - PASSAR APENAS O ID DO USUÁRIO AMIGO
    public ResponseEntity<Void> remove(@RequestBody UsuarioAmigoDTO usuarioAmigoDTO){
      	amigoService.delete(usuarioAmigoDTO);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
  	
	/*
     * ================================================
     * OS MÉTODOS ABAIXO NÃO ESTÃO SENDO USADOS.
     * FORAM FEITOS PARA FINS DE TESTES E APRENDIZADOS.
     * ================================================
     */
	/*
	@ApiOperation(value="Remove um amigo conforme identificador (id)")
  	@DeleteMapping(value="/remove/{id}")
    public ResponseEntity<Void> remove(@RequestBody Long id){
      	amigoService.remove(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
	*/
}
