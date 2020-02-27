package br.com.leonardoangelo.app.service;

import org.springframework.mail.SimpleMailMessage;

import br.com.leonardoangelo.app.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido pedido);
	
	void sendEmail(SimpleMailMessage msg);
}
