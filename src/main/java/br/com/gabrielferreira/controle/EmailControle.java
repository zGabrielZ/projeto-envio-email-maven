package br.com.gabrielferreira.controle;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.gabrielferreira.entidade.Email;

@Named
@ViewScoped
public class EmailControle implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Email email;
	
	String destinatario;
	
	@PostConstruct
	public void init() {
		email = new Email();
	}
	
	public void enviarEmail() {
		email.getDestinatarios().add(destinatario);
		System.out.println(email);
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public Email getEmail() {
		return email;
	}

	public String getDestinatario() {
		return destinatario;
	}

}
