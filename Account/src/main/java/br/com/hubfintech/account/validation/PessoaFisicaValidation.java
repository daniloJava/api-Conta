package br.com.hubfintech.account.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.hubfintech.account.models.PessoaFisica;

public class PessoaFisicaValidation implements Validator {

	public boolean supports(Class<?> clazz) {
		return PessoaFisica.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		
		PessoaFisica pf = (PessoaFisica) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "field.required.pessoaFisica","fodaser");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cpf", "field.required.pessoaFisica","vsadfsfds");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataNascimento", "field.required.pessoaFisica","Fafsdfadsf");
		
	}

}
