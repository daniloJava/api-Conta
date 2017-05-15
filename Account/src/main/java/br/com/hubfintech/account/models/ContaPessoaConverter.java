package br.com.hubfintech.account.models;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ContaPessoaConverter implements AttributeConverter<Conta, String> {
	
	
	public String convertToDatabaseColumn(Conta arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Conta convertToEntityAttribute(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	

}