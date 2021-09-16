package br.com.gabrielferreira.controle;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.gabrielferreira.email.envio.EnvioGmail;
import br.com.gabrielferreira.email.envio.EnvioMailHog;
import br.com.gabrielferreira.entidade.Email;

@Named
@ViewScoped
public class EmailControle implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EnvioMailHog envioMailHog;
	
	@Inject
	private EnvioGmail envioGmail;
	
	private Email email;
	
	private String destinatario;
	
	@PostConstruct
	public void init() {
		email = new Email();
	}
	
	public void enviarEmailComMailHog() {
		email.getDestinatarios().add(destinatario);
		adicionarMensagem("frmEnviarEmail:msg",FacesMessage.SEVERITY_INFO,"E-mail enviado com sucesso !!",null);
		envioMailHog.enviarEmail(email);
		destinatario = null;
		email = new Email();
	}
	
	public void enviarEmailComGmail() {
		email.getDestinatarios().add(destinatario);
		adicionarMensagem("frmEnviarEmailGmail:msg",FacesMessage.SEVERITY_INFO,"E-mail enviado com sucesso !!",null);
		envioGmail.enviarEmail(email);
		destinatario = null;
		email = new Email();
	}
	
	private void adicionarMensagem(String id, Severity severity,String sumario, String detalhe) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		FacesMessage facesMessage = new FacesMessage(severity, sumario, detalhe);
		facesContext.addMessage(id, facesMessage);
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

}
