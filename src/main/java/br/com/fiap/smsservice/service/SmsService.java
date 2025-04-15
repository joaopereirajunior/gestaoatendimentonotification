package br.com.fiap.smsservice.service;

import br.com.fiap.smsservice.model.Mensagem;

public interface SmsService {

	public void notificarEntrada(Mensagem mensagem);
	
	public void notificarSaida(Mensagem mensagem);
	
	public void notificarPrevisao(Mensagem mensagem);
}
