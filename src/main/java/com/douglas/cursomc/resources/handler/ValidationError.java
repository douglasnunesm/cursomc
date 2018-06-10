package com.douglas.cursomc.resources.handler;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{

	
	private List<FieldMessage> errors = new ArrayList<>();
	
	private static final long serialVersionUID = 7687956059850808949L;

	
	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
		// TODO Auto-generated constructor stub
	}


	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String fieldName, String message) {
		errors.add(new FieldMessage(fieldName, message));
	}

	
}
