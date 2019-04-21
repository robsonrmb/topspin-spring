package br.com.ts.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.ts.domain.Acesso;
import br.com.ts.domain.Usuario;
import br.com.ts.dto.CadastroLoginDTO;
import br.com.ts.error.ResourceBadRequestException;
import br.com.ts.service.AcessoService;

@CrossOrigin
@RestController
@RequestMapping(value="/acesso", produces=MediaType.APPLICATION_JSON_VALUE)
public class AcessoResource {
	
	@Autowired
	private AcessoService acessoService;

	@PostMapping
    public ResponseEntity<Usuario> adiciona(@RequestBody CadastroLoginDTO cadastroLoginDTO) {
		Usuario usuario = acessoService.insert(cadastroLoginDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(usuario);
    }
	
	@PostMapping(value="/existe")
    public ResponseEntity<Boolean> isExisteUsuario(@RequestBody Acesso acesso) {
    	verificaSeUsuarioExiste(acesso);
        return ResponseEntity.ok().body(true);	
    }
	
	private void verificaSeUsuarioExiste(Acesso acesso) {
		if (!this.acessoService.isExisteUsuario(acesso)) {
    		throw new ResourceBadRequestException("Dados inválidos!!!");
    	}
	}
    
	/*
     * ================================================
     * OS MÉTODOS ABAIXO NÃO ESTÃO SENDO USADOS.
     * FORAM FEITOS PARA FINS DE TESTES E APRENDIZADOS.
     * ================================================
     */
	/*
	@ApiOperation(value="Altera os dados de login de um usuário conforme dados do formulário.", 
				  notes="Operação não usada.")
    @PutMapping(value="/update")
    public ResponseEntity<Boolean> atualiza(@RequestBody Acesso acesso){
		verificaSeUsuarioExiste(acesso);
    	acessoService.atualiza(acesso);
        return new ResponseEntity<Boolean>(HttpStatus.ACCEPTED);
    }
    
	@ApiOperation(value="Consulta um usuário apartir do identificador (id).", 
			  	  notes="Operação não usada.")
    @GetMapping(value="/{id}")
	public ResponseEntity<Acesso> getUsuario(@PathVariable("id") Long id) {
		Acesso acesso = acessoService.buscaPorId(id);
		return new ResponseEntity<Acesso>(acesso, HttpStatus.OK);	
	}
    
	@ApiOperation(value="Remove um usuário apartir do identificador (id).", 
			  	  notes="Operação não usada. Operação administrativa.")
    @DeleteMapping(value="/remove/{id}")
    public ResponseEntity<Boolean> remove(@PathVariable Long id){
    	acessoService.exclui(id);
        return new ResponseEntity<Boolean>(HttpStatus.ACCEPTED);
    }
	*/
}
