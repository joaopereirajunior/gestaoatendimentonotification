package br.com.fiap.smsservice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.smsservice.mapper.MensagemMapper;
import br.com.fiap.smsservice.model.Mensagem;
import br.com.fiap.smsservice.model.dto.SmsRequestDTO;
import br.com.fiap.smsservice.service.SmsService;

@RestController
@RequestMapping("/api/notificacoes")
public class SmsController {

	private final SmsService smsService;
	private final MensagemMapper mensagemMapper;
	
	public SmsController(SmsService smsService, MensagemMapper mensagemMapper) {
		this.smsService = smsService;
		this.mensagemMapper = mensagemMapper;
	}
	
	@PostMapping("/entrada")
	public void notificarEntrada(@RequestBody SmsRequestDTO request) {
		Mensagem mensagem = mensagemMapper.converterSmsRequestDtoParaMensagem(request);
		smsService.notificarEntrada(mensagem);
	}
	
	@PostMapping("/saida")
	public void notificarSaida(@RequestBody SmsRequestDTO request) {
		Mensagem mensagem = mensagemMapper.converterSmsRequestDtoParaMensagem(request);
		smsService.notificarSaida(mensagem);
	}
	
	@PostMapping("/previsao")
	public void notificarPrevisao(@RequestBody SmsRequestDTO request) {
		Mensagem mensagem = mensagemMapper.converterSmsRequestDtoParaMensagem(request);
		smsService.notificarPrevisao(mensagem);
	}
}
