package br.com.ts.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.ts.service.mail.EmailService;
import br.com.ts.service.mail.SmtpEmailService;

@Configuration
@Profile("test")
public class TestConfig {

	@Bean
	public boolean iniciandoProfileLocal() {
		
		System.out.println("Inicializando profile: test!!!");
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
	
}
