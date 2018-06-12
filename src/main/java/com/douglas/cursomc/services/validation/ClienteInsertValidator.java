package com.douglas.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.douglas.cursomc.domains.enums.TipoCliente;
import com.douglas.cursomc.dto.ClienteNewDTO;
import com.douglas.cursomc.repositories.ClienteRepository;
import com.douglas.cursomc.resources.handler.FieldMessage;
import com.douglas.cursomc.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	transient ClienteRepository clienteRepository;

	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfCpnj()))
			list.add(new FieldMessage("cpfCpnj", "CPF inválido"));
		
		if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfCpnj()))
			list.add(new FieldMessage("cpfCpnj", "CNPJ inválido"));
		
		if(clienteRepository.findByEmail(objDto.getEmail()) != null)
			list.add(new FieldMessage("email", "E-mail já utilizado."));
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}