package br.com.hubfintech.account.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.hubfintech.account.models.PessoaJuridica;

public class PessoaJuridicaValidation implements Validator {

	public boolean supports(Class<?> clazz) {
		return PessoaJuridica.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "razaoSocial", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "cnpj", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "nomeFantasia", "field.required");
	}

}
