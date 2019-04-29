package br.com.ts.service.mail;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import br.com.ts.domain.Convite;
import br.com.ts.domain.Usuario;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;
	
	@Override
	public void sendOrderConfirmationEmail(Convite convite) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromConvite(convite);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromConvite(Convite convite) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(convite.getUsuario().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Convite realizado. " + convite.getStatus());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(convite.toString());
		return sm;
	}
	
	@Override
	public void sendNewPasswordEmail(Usuario usuario, String novaSenha) {
		SimpleMailMessage sm = prepareNewPasswordEmail(usuario, novaSenha);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareNewPasswordEmail(Usuario usuario, String novaSenha) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(usuario.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Solicitação de nova senha");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Nova senha: " + novaSenha);
		return sm;
	}
		
}
