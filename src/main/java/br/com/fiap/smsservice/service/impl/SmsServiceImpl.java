package br.com.fiap.smsservice.service.impl;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import br.com.fiap.smsservice.model.Mensagem;
import br.com.fiap.smsservice.service.SmsService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SmsServiceImpl implements SmsService {
	
	@Value("${twilio.account.sid}")
	private String twilioSid;

	@Value("${twilio.auth.token}")
	private String twilioKey;

	@Value("${twilio.phone.from}")
	private String twilioPhoneFrom;
	
	private static final Logger logger = LoggerFactory.getLogger(SmsServiceImpl.class);
	
	@Override
	public void notificarEntrada(Mensagem mensagem) {
		
		String texto = MessageFormat.format("Olá {0}, você se registrou em nossa fila de atendimento"
				+ " e se encontra na posição {1}.", mensagem.getNome(), mensagem.getPosicao()); 
		
		enviarSms(mensagem.getNumero(), texto);
	}

	@Override
	public void notificarSaida(Mensagem mensagem) {
		String texto = "Chegou a sua vez de ser atendido, você é o primeiro da fila!";
		
		enviarSms(mensagem.getNumero(), texto);
	}

	@Override
	public void notificarPrevisao(Mensagem mensagem) {
		
		int previsao = Integer.parseInt(mensagem.getPosicao()) * 5;
		
		String texto = MessageFormat.format("Você se encontra na posição {0} da fila de atendimento, "
				+ "o tempo estimado de previsão é {1} minutos.", mensagem.getPosicao(), previsao); 
		
		enviarSms(mensagem.getNumero(), texto);
	}

	public void enviarSms(String toNumber, String textoMensagem) {
		
		try {
			Twilio.init(twilioSid, twilioKey);

			PhoneNumber to = new PhoneNumber(toNumber);
			PhoneNumber from = new PhoneNumber(twilioPhoneFrom);

			Message message = Message.creator(to, from, textoMensagem).create();

			logger.info("SMS enviado com sucesso. SID: {}", message.getSid());
		} catch (Exception e) {
			logger.error("Erro ao enviar SMS para o número {}: {}", toNumber, e.getMessage(), e);
	        throw new RuntimeException("Erro ao enviar SMS", e);
		}
	}
}
