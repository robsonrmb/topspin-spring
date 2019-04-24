package br.com.ts.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

	@Bean
	public boolean iniciandoProfileLocal() {
		
		System.out.println("Inicializando profile: test!!!");
		return true;
	}
	
}
