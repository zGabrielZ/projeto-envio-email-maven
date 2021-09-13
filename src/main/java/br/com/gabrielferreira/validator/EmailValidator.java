package br.com.gabrielferreira.validator;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

@Named
@RequestScoped
public class EmailValidator implements Validator{

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String email = (String) value;
		
		if(!validarEmail(email)) {
			String descricao = "Não é possível seguir em frente com este e-mail, pois não é válido.";
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, descricao, null);
			throw new ValidatorException(facesMessage);
		}
		
	}
	
	private boolean validarEmail(String email) {
		boolean resultadoEmailVerdadeiro = true;
		try {
			InternetAddress internetAddress = new InternetAddress(email);
			internetAddress.validate();
		} catch (AddressException e) {
			resultadoEmailVerdadeiro = false;
		}
		return resultadoEmailVerdadeiro;
	}

}
