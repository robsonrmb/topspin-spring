package br.com.ts.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.ts.service.DBService;
import br.com.ts.service.mail.EmailService;
import br.com.ts.service.mail.MockEmailService;

@Configuration
@Profile("test")
public class TestConfig {

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Autowired
	private DBService dbService;
	
	@Bean
	public boolean iniciandoProfileLocal() throws ParseException {
		
		System.out.println("Inicializando profile: test!!!");
		System.out.println("Utilizando banco de dados H2.");
		System.out.println("Estratégia de criação de banco: "+ strategy); 
		
		dbService.instanciandoBancoDeDadosH2();
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}
	
}
