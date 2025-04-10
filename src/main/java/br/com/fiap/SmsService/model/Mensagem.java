package br.com.fiap.SmsService.model;

public class Mensagem {

	String nome;
	String numero;
	String posicao;
	
	public Mensagem(String nome, String numero, String posicao) {
		this.nome = nome;
		this.numero = numero;
		this.posicao = posicao;
	}

	public String getNome() {
		return nome;
	}

	public String getNumero() {
		return numero;
	}

	public String getPosicao() {
		return posicao;
	}
}
