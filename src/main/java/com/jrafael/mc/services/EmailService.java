package com.jrafael.mc.services;

import org.springframework.mail.SimpleMailMessage;

import com.jrafael.mc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	
}
