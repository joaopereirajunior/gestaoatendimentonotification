package br.com.fiap.smsservice.model.dto;

public record SmsRequestDTO (
		String nome,
		String numero,
		String posicao
) {}
