package br.com.ts.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ts.dto.CredenciaisDTO;
/*
 * FILTRO RESPONSÁVEL POR INTERCEPTAR A REQUISIÇÃO E VERIFICAR SE O USUÁRIO ESTÁ CORRETO.
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private AuthenticationManager authenticationManager;
	private JWTUtil jwtUtil;
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}
	
	//TENTANDO AUTENTICAR A REQUISIÇÃO DO USUÁRIO
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		try {
			CredenciaisDTO creds = new ObjectMapper()
					.readValue(request.getInputStream(), CredenciaisDTO.class);
			
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(creds.getEmail(),  creds.getSenha(), new ArrayList<>());
			
			//VERIFICANDO COM BASE NOS CONTRATOS (UserSS e UserDetailService) SE O USUÁRIO É VÁLIDO.
			Authentication auth = authenticationManager.authenticate(authToken); 
			return auth;
		
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	//SENDO A AUTENTICAÇÃO VÁLIDA, DEVE GERAR O TOKEN E ACRESCENTAR NA RESPOSTA DA REQUISIÇÃO.
	@Override
	protected void successfulAuthentication(HttpServletRequest request, 
										 	HttpServletResponse response,
										 	FilterChain chain,
										 	Authentication auth) throws IOException, ServletException {
		
		String username = ((UserSS) auth.getPrincipal()).getUsername();
		String token = jwtUtil.generateToken(username);
		response.addHeader("Autorization", "Bearer "+ token);
		
	}

}