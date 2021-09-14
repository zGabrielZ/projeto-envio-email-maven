package br.com.gabrielferreira.email.layout;

import java.io.Serializable;

public class EmailLayout implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static String QUEBRA_LINHA = "<br/><br/>";
	
	public String layoutEmail() {
		StringBuilder sb = new StringBuilder();
		sb.append("<p>Boa tarde senhor ou senhora.</p>")
			.append(QUEBRA_LINHA);
		
		sb.append("<p>Não se preocupe com esse e-mail enviado, é só uma simulação de envio feito em Java, utilizando Java Mail !!</p>")
			.append(QUEBRA_LINHA);
		
		sb.append("<p>Tenha uma boa semana !!</p>");
		return sb.toString();
	}

}
