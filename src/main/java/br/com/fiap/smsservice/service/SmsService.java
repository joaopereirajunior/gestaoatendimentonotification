package br.com.fiap.smsservice.service;

import org.springframework.stereotype.Service;

import br.com.fiap.smsservice.model.Mensagem;

@Service
public interface SmsService {

	public void notificarEntrada(Mensagem mensagem);
	
	public void notificarSaida(Mensagem mensagem);
	
	public void notificarPrevisao(Mensagem mensagem);
}
