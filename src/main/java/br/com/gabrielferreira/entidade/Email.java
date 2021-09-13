package br.com.gabrielferreira.entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Email implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nomeRemetente;
	private String titulo;
	private String assunto;
	private List<String> destinatarios = new ArrayList<>();
	
	public Email() {}

	public String getNomeRemetente() {
		return nomeRemetente;
	}

	public void setNomeRemetente(String nomeRemetente) {
		this.nomeRemetente = nomeRemetente;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public List<String> getDestinatarios() {
		return destinatarios;
	}

	public void setDestinatarios(List<String> destinatarios) {
		this.destinatarios = destinatarios;
	}

	@Override
	public String toString() {
		return "Email [nomeRemetente=" + nomeRemetente + ", titulo=" + titulo + ", assunto=" + assunto
				+ ", destinatarios=" + destinatarios + "]";
	}
	
	

}
