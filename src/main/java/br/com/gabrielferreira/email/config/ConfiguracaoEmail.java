package br.com.gabrielferreira.email.config;

import java.util.Properties;

import javax.mail.Session;

import br.com.gabrielferreira.entidade.Email;

public interface ConfiguracaoEmail {

	public Properties definirPropriedades();
	
	public Session conectarServidor(Properties properties);
	
	public void enviarEmail(Email email);
}
