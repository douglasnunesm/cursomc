package com.douglas.cursomc.services.exceptions;

public class AuthorizationException extends RuntimeException {

	private static final long serialVersionUID = 2958066628286352569L;

	public AuthorizationException(String msg) {
		super(msg);
	}

	public AuthorizationException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
