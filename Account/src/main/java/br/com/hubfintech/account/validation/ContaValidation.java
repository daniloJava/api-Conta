package br.com.hubfintech.account.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.hubfintech.account.models.Conta;

public class ContaValidation implements Validator{

	public boolean supports(Class<?> clazz) {
		return Conta.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "nome", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "status", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "tipoConta", "field.required");
		
	}

}
