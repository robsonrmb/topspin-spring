package br.com.ts.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.com.ts.security.JWTAuthenticationFilter;
import br.com.ts.security.JWTAuthorizationFilter;
import br.com.ts.security.JWTUtil;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // Para autorização de perfis específicos.
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	private static final String[] PUBLIC_MATCHERS_H2 = {
		"/h2-console/**"
	};
	
	private static final String[] PUBLIC_MATCHERS_GET_TS = {
		"/amigos/**",
		"/area-avaliacoes/**",
		"/avaliacoes/**",
		"/convites/**",
		"/estatisticas/**",
		"/jogos/**",
		"/tipoavaliacoes/**",
		"/usuarios/**"
	};
	
	private static final String[] PUBLIC_MATCHERS_POST_TS = {
		"/usuarios/**",
		"/auth/nova_senha/**"
	};
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
			//Somente para dar acesso ao banco H2
			http.headers().frameOptions().disable();
		}
		
		//Autorizar acesso a outras fontes: cors(). Utiliza o bean corsConfigurationSource() criado abaixo.
		//Desabilitar ataques CSRF que é baseado no armazenamento de autenticação da sessão.
		http.cors().and().csrf().disable();
		
		//Autorizar ou bloquear usando o .antMatchers
		http.authorizeRequests()
			.antMatchers(PUBLIC_MATCHERS_H2).permitAll()
			//.antMatchers(PUBLIC_MATCHERS_GET_TS).permitAll()
			.antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET_TS).permitAll()
			.antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST_TS).permitAll()
			.anyRequest().authenticated();
		
		http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
		http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService));
		
		//Não deixar criar sessão do usuário
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	/*
	 * Devemos informar duas coisas neste método:
	 * 1. Quem é o userDetail que estamos usando: userDetailService.
	 * 2. Quem é o algoritmo de codificação da senha: BCryptPasswordEncode.
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
		configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));;
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
