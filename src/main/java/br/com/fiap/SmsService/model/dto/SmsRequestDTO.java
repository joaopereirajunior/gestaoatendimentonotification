package br.com.fiap.SmsService.model.dto;

public record SmsRequestDTO (
		String nome,
		String numero,
		String posicao
) {}
