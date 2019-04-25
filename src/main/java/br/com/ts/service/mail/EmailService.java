package br.com.ts.service.mail;

import org.springframework.mail.SimpleMailMessage;

import br.com.ts.domain.Convite;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Convite convite);
	void sendEmail(SimpleMailMessage msg);

}
