package br.com.gabrielferreira.teste.email;

import org.junit.Test;

import br.com.gabrielferreira.email.envio.EnvioMailHog;
import br.com.gabrielferreira.email.layout.EmailLayout;
import br.com.gabrielferreira.entidade.Email;

public class EnvioEmailMailHog {

	private EmailLayout emailLayout = new EmailLayout();
	private EnvioMailHog envioMailHog = new EnvioMailHog();
	private Email email = new Email();
	
	@Test
	public void enviarEmail() {
		email.setAssunto(emailLayout.layoutEmail());
		email.setTitulo("Envio de e-mail via JavaMail");
		email.getDestinatarios().add("ferreiragabriel2612@gmail.com");
		envioMailHog.enviarEmail(email);
		System.out.println("E-mail enviado com sucesso !!");
	}
	
}
