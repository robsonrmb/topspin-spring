package br.com.ts.service.mail;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import br.com.ts.domain.Convite;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;
	
	@Override
	public void sendOrderConfirmationEmail(Convite convite) {
		SimpleMailMessage sm = PrepareSimpleMailMessageFromPedido(convite);
		sendEmail(sm);
	}

	protected SimpleMailMessage PrepareSimpleMailMessageFromPedido(Convite convite) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(convite.getUsuario().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Convite realizado. " + convite.getStatus());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(convite.toString());
		return sm;
	}
		
}
