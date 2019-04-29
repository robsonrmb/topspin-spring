package br.com.ts.service.mail;

import org.springframework.mail.SimpleMailMessage;

import br.com.ts.domain.Convite;
import br.com.ts.domain.Usuario;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Convite convite);
	void sendEmail(SimpleMailMessage msg);
	void sendNewPasswordEmail(Usuario usuario, String novaSenha);

}
