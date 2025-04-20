package br.com.fiap.smsservice.mapper;

import org.springframework.stereotype.Component;

import br.com.fiap.smsservice.model.Mensagem;
import br.com.fiap.smsservice.model.dto.SmsRequestDTO;

@Component
public class MensagemMapper {

	public Mensagem converterSmsRequestDtoParaMensagem(SmsRequestDTO request) {
		
		return new Mensagem(request.nome(), request.numero(), request.posicao());
	}
}
