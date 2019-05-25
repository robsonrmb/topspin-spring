package br.com.ts.service.mail;

import org.springframework.mail.SimpleMailMessage;

import br.com.ts.domain.Convite;
import br.com.ts.domain.Usuario;

/*
 * EXPLICAÇÃO DA ESTRUTURA DE EMAIL
 * 1. CRIAR A INTERFACE: EmailService
 * 2. CRIAR A CLASSE ABSTRATA: AbstractEmailService QUE IMPLEMENTA EmailService
 * 3. OS ARQUIVOS DE CONFIGURAÇÃO DO SPRING (LocalConfig e TestConfig - Profiles)
 * 	  CRIARÁ UMA INSTANCIA DE EmailService AO INICIALIZAR O PROJETO - VER MÉTODO emailService.
 * 	  ESTA INSTÂNCIA RETORNARÁ UM MockEmailService ou SmtpEmailService QUE EXTENDE O
 * 	  AbstractEmailService CONTENDO O MÉTODO sendEmail DEFINIDO NA INTERFACE EmailService.
 * 4. PARA USAR, BASTA INJETAR O EmailService e utilizar o método de envio, como o exemplo abaixo:
 * 	  
 * 	  @Autowired
 * 	  private EmailService emailService;
 * 
 * 	  emailService.sendNewPasswordEmail(usuario, novaSenha);
 * 
 * 	  Exemplo feito no projeto: envio de convite e envio de geração para nova senha.
 */

public interface EmailService {
	
	void sendOrderConfirmationEmail(Convite convite);
	void sendEmail(SimpleMailMessage msg);
	void sendNewPasswordEmail(Usuario usuario, String novaSenha);

}

