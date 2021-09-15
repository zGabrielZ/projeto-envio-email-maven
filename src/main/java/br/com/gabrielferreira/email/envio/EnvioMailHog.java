package br.com.gabrielferreira.email.envio;

import java.io.Serializable;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import br.com.gabrielferreira.email.config.ConfiguracaoEmail;
import br.com.gabrielferreira.entidade.Email;

public class EnvioMailHog implements ConfiguracaoEmail, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String EMAIL_USUARIO_FINAL = "ferreiragab261297@gmail.com";

	// Para o MailHog, foi definidos as propriedades no standalone-full.xml
	
//	<system-properties>
//		<property name="envio-email.mail.smtp.host" value="localhost"  />
//		<property name="envio-email.mail.smtp.port" value="1025"  />
//		<property name="envio-email.mail.from" value="ferreiragab261297@gmail.com"  />
//	</system-properties>
	
	@Override
	public Properties definirPropriedades() {
		Properties properties = new Properties();
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.smtp.host", "localhost");
		properties.put("mail.smtp.port", "1025");
		return properties;
	}
	
	@Override
	public Session conectarServidor(Properties properties) {
		Session session = Session.getDefaultInstance(properties);
		session.setDebug(false);
		return session;
	}

	@Override
	public void enviarEmail(Email email) {
		Properties properties = definirPropriedades();
		Session session = conectarServidor(properties);
		
		// Enviar o e-mail
		email.getDestinatarios().add(EMAIL_USUARIO_FINAL);
		try {
			
			String emails = email.getDestinatarios().toString();
			String emailsFormatados = emails.substring(1, emails.length() - 1);
			Address[] destinatarios = InternetAddress.parse(emailsFormatados);
			
			Message message = new MimeMessage(session);
			
			// Quem está enviando 
			message.setFrom(new InternetAddress(EMAIL_USUARIO_FINAL));
			
			// Email de destino 
			message.setRecipients(Message.RecipientType.TO, destinatarios);
			
			// Titulo do e-mail
			message.setSubject(email.getTitulo());
			
			// Texto do e-mail em formato HTML
			message.setContent(email.getAssunto(), "text/html; charset=utf-8");
			
			// Enviando e-mail
			Transport.send(message);
				
		} catch (Exception e) {
			Logger.getLogger(e.getMessage());
		}
		
	}

}
