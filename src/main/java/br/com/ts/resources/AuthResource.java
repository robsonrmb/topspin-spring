package br.com.ts.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.ts.dto.EmailDTO;
import br.com.ts.security.JWTUtil;
import br.com.ts.security.UserSS;
import br.com.ts.service.AuthService;
import br.com.ts.service.AuthenticationService;

@CrossOrigin
@RestController
@RequestMapping(value="/auth", produces=MediaType.APPLICATION_JSON_VALUE)
public class AuthResource {
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AuthService authService;
	
	@RequestMapping(value="/refresh_token", method=RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSS userSS = AuthenticationService.getUsuarioAutenticado();
		// GERANDO NOVO TOKEN
		String token = jwtUtil.generateToken(userSS.getEmail()); //ou getUsername()
		
		// INSERINDO TOKEN NO HEADER DA REQUISIÇÃO
		response.addHeader("Authorization", "Bearer " + token);
		response.addHeader("access-control-expose-headers", "Authorization"); 
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/nova_senha", method=RequestMethod.POST)
	public ResponseEntity<Void> novaSenha(@Valid @RequestBody EmailDTO emailDTO) {
		authService.sendNewPassword(emailDTO.getEmail());
		return ResponseEntity.noContent().build();
	}

}
