package br.com.hubfintech.account.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.hubfintech.account.models.Pessoa;
import br.com.hubfintech.account.models.PessoaJuridica;

public class PessoaValidation implements Validator {

	public boolean supports(Class<?> clazz) {
		return Pessoa.class.isAssignableFrom(clazz);
	}

	public void validate(Object arg0, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "conta", "field.required");
	}

}
