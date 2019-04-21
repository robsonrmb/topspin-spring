package br.com.ts.resources;

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
import org.springframework.web.bind.annotation.RestController;

import br.com.ts.domain.Usuario;
import br.com.ts.service.UsuarioService;

@CrossOrigin()
@RestController
@RequestMapping(value="/usuarios", produces=MediaType.APPLICATION_JSON_VALUE)
public class UsuarioResource {
	
	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<List<Usuario>> getListaTodos() {
		List<Usuario> listaUsuarios = usuarioService.findAll();
		return ResponseEntity.ok().body(listaUsuarios);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Usuario> getUsuario(@PathVariable("id") Long id) {
		Usuario usuario = usuarioService.find(id);
		return ResponseEntity.ok().body(usuario);
	}
	
	@PutMapping(value="/{id}")
    public ResponseEntity<Void> atualiza(@RequestBody Usuario usuario, @PathVariable("id") Long id) {
		usuario.setId(id);
      	usuarioService.update(usuario);
        return ResponseEntity.noContent().build();
    }
  	
  	//SOMENTE ADMINISTRATIVO
    @DeleteMapping(value="/remove/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id){
      	usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    //SOMENTE ADMINISTRATIVO OU O PROPRIO USUÁRIO
  	@PutMapping(value="/desativa/{id}")
    public ResponseEntity<Void> desativa(@PathVariable Long id){
      	usuarioService.desativa(id);
      	return ResponseEntity.noContent().build();
  	}
  	
  	
	
	//PERSONALIZADOS ################################################################
	
	@GetMapping(value="/filterEmail/{email}")
    public ResponseEntity<Usuario> buscaPorEmail(@PathVariable String email) {
    	Usuario usuario = this.usuarioService.buscaPorEmail(email);
        return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);	
    }
    
    @GetMapping(value="/filterEstado/{estado}")
    public ResponseEntity<List<Usuario>> listaPorEstado(@PathVariable String estado) {
    	List<Usuario> listaUsuarios = this.usuarioService.listaPorEstado(estado);
        return new ResponseEntity<List<Usuario>>(listaUsuarios, HttpStatus.OK);	
    }
    
    @GetMapping(value="/filterNome/{nome}")
    public ResponseEntity<List<Usuario>> listaPorNome(@PathVariable String nome) {
    	List<Usuario> listaUsuarios = this.usuarioService.listaPorNome(nome);
        return new ResponseEntity<List<Usuario>>(listaUsuarios, HttpStatus.OK);	
    }
    
    @PostMapping(value="/filter")
    public ResponseEntity<List<Usuario>> listaPorFiltro(@RequestBody Usuario usuario) {
    	List<Usuario> listaUsuarios = this.usuarioService.listaPorFiltro(usuario);
        return new ResponseEntity<List<Usuario>>(listaUsuarios, HttpStatus.OK);	
    }
    
    @PostMapping(value="/filterComFlagAmigo")
    public ResponseEntity<List<Usuario>> listaPorFiltroComFlagAmigo(@RequestBody Usuario usuario) {
    	List<Usuario> listaUsuarios = this.usuarioService.listaPorFiltroComFlagAmigo(usuario);
    	List<Usuario> listaUsuariosFinal = new ArrayList<Usuario>();
    	for (Usuario u: listaUsuarios) {
    		if(!usuario.getId().equals(u.getId())) {
    			listaUsuariosFinal.add(u);
    		}
    	}
        return new ResponseEntity<List<Usuario>>(listaUsuariosFinal, HttpStatus.OK);	
    }
    
    /*
    @GetMapping(value="/filter")
    public ResponseEntity<List<Usuario>> listaPorFiltro(@RequestParam(name="nome", required=false) String nome,
    													@RequestParam(name="email", required=false) String email,
    													@RequestParam(name="ondeJoga", required=false) String ondeJoga,
    													@RequestParam(name="tipo", required=false) String tipo,
    													@RequestParam(name="nivel", required=false) String nivel,
    													@RequestParam(name="estado", required=false) String estado) {
    	
    	//Usuario usuario = new Usuario(nome, email, ondeJoga, tipo, nivel, estado);
       
        List<Usuario> listaUsuarios = this.usuarioService.listaPorFiltros(usuario);
        return new ResponseEntity<List<Usuario>>(listaUsuarios, HttpStatus.OK);	
    }
    */

}
