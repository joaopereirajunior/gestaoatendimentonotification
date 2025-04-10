package br.com.fiap.SmsService.service;

import org.springframework.stereotype.Service;

import br.com.fiap.SmsService.model.Mensagem;

@Service
public interface SmsService {

	public void notificarEntrada(Mensagem mensagem);
	
	public void notificarSaida(Mensagem mensagem);
	
	public void notificarPrevisao(Mensagem mensagem);
}
