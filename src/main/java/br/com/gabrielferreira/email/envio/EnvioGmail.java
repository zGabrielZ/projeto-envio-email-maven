package br.com.gabrielferreira.email.envio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.inject.Inject;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.apache.log4j.Logger;

import br.com.gabrielferreira.email.config.ConfiguracaoEmail;
import br.com.gabrielferreira.entidade.Email;
import br.com.gabrielferreira.servico.UsuarioServico;

public class EnvioGmail implements ConfiguracaoEmail, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String EMAIL_USUARIO_FINAL = "ferreiragab261297@gmail.com";
	private static String NOME_USUARIO_FINAL = "Gabriel Ferreira";
	private static String SENHA_FINAL = "";
	
	@Inject
	private UsuarioServico usuarioServico;

	@Override
	public Properties definirPropriedades() {
		Properties properties = new Properties();
		properties.put("mail.smtp.ssl.trust", "*"); // Autenticar com segurança SSL
		properties.put("mail.smtp.auth", "true"); // Autorização
		properties.put("mail.smtp.starttls", "true"); // Segurança ou Autenticação
		properties.put("mail.smtp.host", "smtp.gmail.com"); // Servidor
		properties.put("mail.smtp.port","465"); // Porta do Servidor
		properties.put("mail.smtp.socketFactory.port", "465"); // Porta a ser conectado via socket
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); // Classe Socket de conexão ao smtp
		return properties;
	}

	@Override
	public Session conectarServidor(Properties properties) {
		// Autenticação ao Servidor

		Authenticator authenticator = new Authenticator() {

			// Passar o email e a senha que vai enviar o email

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(EMAIL_USUARIO_FINAL, SENHA_FINAL);
			}

		};

		Session session = Session.getDefaultInstance(properties, authenticator);
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
			message.setFrom(new InternetAddress(EMAIL_USUARIO_FINAL,NOME_USUARIO_FINAL));
			
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
	
	public void enviarEmailComPdf(Email email) {
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
			message.setFrom(new InternetAddress(EMAIL_USUARIO_FINAL,NOME_USUARIO_FINAL));
			
			// Email de destino 
			message.setRecipients(Message.RecipientType.TO, destinatarios);
			
			// Titulo do e-mail
			message.setSubject(email.getTitulo());
			
			// Primeira parte -> so o texto,titulo, corpo do email
			MimeBodyPart corpoEmail = new MimeBodyPart();	
			// Texto do e-mail em formato HTML
			corpoEmail.setContent(email.getAssunto(), "text/html; charset=utf-8");
			
			// Segunda parte -> so o anexo do pdf
			MimeBodyPart anexoEmail = new MimeBodyPart();
			anexoEmail.setDataHandler(new DataHandler(new ByteArrayDataSource(geracaoPdfUsuario(), "application/pdf")));
			anexoEmail.setFileName("Relatório de usuários.pdf");
			
			// Juntando essas duas partes 
			
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(corpoEmail);
			multipart.addBodyPart(anexoEmail);
			
			message.setContent(multipart);
			
			// Enviando e-mail
			Transport.send(message);
				
		} catch (Exception e) {
			Logger.getLogger(e.getMessage());
		}
	}
	
	// Esse método vai pegar o byte[] do nosso pdf e seta como anexo no envio de email
 	private FileInputStream geracaoPdfUsuario() throws IOException {
		File file = new File("Relatório de Usuários.pdf");
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		fileOutputStream.write(usuarioServico.gerarRelatorioUsuario());
		fileOutputStream.flush();
		fileOutputStream.close();
		return new FileInputStream(file);
	}
	
	

}
