package br.com.fiap.SmsService.mapper;

import org.springframework.stereotype.Component;

import br.com.fiap.SmsService.model.Mensagem;
import br.com.fiap.SmsService.model.dto.SmsRequestDTO;

@Component
public class MensagemMapper {

	public Mensagem converterSmsRequestDtoParaMensagem(SmsRequestDTO request) {
		
		return new Mensagem(request.nome(), request.numero(), request.posicao());
	}
}
