package br.com.ts.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.ts.service.DBService;
import br.com.ts.service.mail.EmailService;
import br.com.ts.service.mail.MockEmailService;
import br.com.ts.service.mail.SmtpEmailService;

@Configuration
@Profile("local")
public class LocalConfig {

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Autowired
	private DBService dbService;
	
	@Bean
	public boolean iniciandoProfileLocal() {
		
		System.out.println("Inicializando profile: local");
		System.out.println("Utilizando banco de dados MySQL.");
		System.out.println("Estratégia de criação de banco: "+ strategy);
		
		//dbService.instanciandoBancoDeDadosMySQL();
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new MockEmailService(); //SmtpEmailService();
	}
	
}
