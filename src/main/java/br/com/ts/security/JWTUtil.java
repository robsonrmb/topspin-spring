package br.com.ts.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

@Component
public class JWTUtil {
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private Long expiration;
	
	/*
	 * MÉTODO RESPONSÁVEL POR GERAR O TOKEN CONFORME USUÁRIO, NO CASO O EMAIL DO USUÁRIO
	 */
	public String generateToken(String email) {
		return Jwts.builder()
				.setSubject(email)
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes()) //qual o algoritmo e o segredo do meu token.
				.compact();
	}

	
	public boolean tokenValido(String token) {
		// VALIDANDO O USUÁRIO
		Claims claims = getClaims(token);
		if (claims != null) {
			String username = claims.getSubject();
			Date expirationDate = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis());
			
			if (username != null && expirationDate != null && now.before(expirationDate)) {
				return true;
			}
		}
		return false;
	}

	// FUNÇÃO QUE RECUPERA OS CLIENTES A PARTIR DE UM TOKEN.
	private Claims getClaims(String token) {
		try {
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
		
		}catch(Exception e) {
			return null;
		}
	}

	// RECUPERANDO O USUÁRIO A PARTIR DO TOKEN
	public String getUsername(String token) {
		Claims claims = getClaims(token);
		if (claims != null) {
			return claims.getSubject();
		}
		return null;
	}

}
