package com.douglas.cursomc.resources.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.douglas.cursomc.services.exceptions.AuthorizationException;
import com.douglas.cursomc.services.exceptions.DataIntegrityException;

import javassist.tools.rmi.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		
		StandardError standardError = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
	}
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegity(DataIntegrityException e, HttpServletRequest request){
		
		StandardError standardError = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request){
		
		ValidationError validationError = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro Validação", System.currentTimeMillis());
		
		for(FieldError field : e.getBindingResult().getFieldErrors())
			validationError.addError(field.getField(), field.getDefaultMessage());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError);
	}
	
	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<StandardError> accessDenied(AuthorizationException e, HttpServletRequest request){
		
		ValidationError validationError = new ValidationError(HttpStatus.FORBIDDEN.value(), "Acesso Negado", System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(validationError);
	}
}
