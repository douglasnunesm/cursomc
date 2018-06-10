package com.douglas.cursomc.resources.handler;

import java.io.Serializable;

public class FieldMessage implements Serializable {

	private static final long serialVersionUID = 7691446593334767204L;

	private String fieldName;
	private String message;

	public FieldMessage() {
		// TODO Auto-generated constructor stub
	}

	public FieldMessage(String fieldMessage, String message) {
		super();
		this.fieldName = fieldMessage;
		this.message = message;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
