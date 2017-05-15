package br.com.hubfintech.account.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.hubfintech.account.models.Transferencia;

public class TransferenciaValidation implements Validator{

	public boolean supports(Class<?> clazz) {
		return Transferencia.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors Errors) {
		
	}
	

}
