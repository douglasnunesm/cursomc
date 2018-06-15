package com.douglas.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.douglas.cursomc.domains.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
