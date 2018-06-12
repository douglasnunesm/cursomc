package com.douglas.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.douglas.cursomc.domains.Cliente;
import com.douglas.cursomc.dto.ClienteDTO;
import com.douglas.cursomc.repositories.ClienteRepository;
import com.douglas.cursomc.resources.handler.FieldMessage;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
	
	@Autowired
	transient HttpServletRequest request;
	
	@Autowired
	transient ClienteRepository clienteRepository;

	@Override
	public void initialize(ClienteUpdate ann) {
	}

	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
 		
		List<FieldMessage> list = new ArrayList<>();
		Cliente cliente = clienteRepository.findByEmail(objDto.getEmail());
		
		if(cliente != null && !cliente.getId().equals(Integer.valueOf(map.get("id"))))
			list.add(new FieldMessage("email", "E-mail já cadastrado."));
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}