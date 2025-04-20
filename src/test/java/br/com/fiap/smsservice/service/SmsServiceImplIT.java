package br.com.fiap.smsservice.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import br.com.fiap.smsservice.SmsServiceApplication;
import br.com.fiap.smsservice.model.Mensagem;
import br.com.fiap.smsservice.service.impl.SmsServiceImpl;

@ActiveProfiles("test")
@SpringBootTest(classes = SmsServiceApplication.class,
				webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SmsServiceImplIT {
	
	@LocalServerPort
	private int port;
	
	@Autowired
    private SmsServiceImpl smsServiceImpl;
	
	private static final String NUMERO_SUCESSO = "+15005550006";
	private static final String NUMERO_INVALIDO = "+15005550001";
	
	@Test
	void deveNotificarEntradaComSucesso() {
		// Arrange
		Mensagem mensagem = new Mensagem("Lucas", NUMERO_SUCESSO, "2");
		
	    // Act & Assert
	    assertDoesNotThrow(() -> smsServiceImpl.notificarEntrada(mensagem));
	}
	
	@Test
	void deveFalharAoNotificarEntrada_ComNumeroInvalido() {
	    // Arrange
	    Mensagem mensagem = new Mensagem("Lucas", NUMERO_INVALIDO, "2");

	    // Act & Assert
	    RuntimeException excecao = assertThrows(RuntimeException.class, () -> 
	        smsServiceImpl.notificarEntrada(mensagem)
	    );

	    assertTrue(excecao.getMessage().contains("Erro ao enviar SMS"));
	}
	
	@Test
	void deveNotificarSaidaComSucesso() {
	    // Arrange
	    Mensagem mensagem = new Mensagem("Lucas", NUMERO_SUCESSO, "1");

	    // Act & Assert
	    assertDoesNotThrow(() -> smsServiceImpl.notificarSaida(mensagem));
	}
	
	@Test
	void deveFalharAoNotificarSaida_ComNumeroInvalido() {
	    // Arrange
	    Mensagem mensagem = new Mensagem("Lucas", NUMERO_INVALIDO, "1");

	    // Act & Assert
	    RuntimeException e = assertThrows(RuntimeException.class, () -> 
	        smsServiceImpl.notificarSaida(mensagem)
	    );

	    assertTrue(e.getMessage().contains("Erro ao enviar SMS"));
	}
	
	@Test
	void deveNotificarPrevisaoComSucesso() {
	    // Arrange
	    Mensagem mensagem = new Mensagem("Lucas", NUMERO_SUCESSO, "3");

	    // Act & Assert
	    assertDoesNotThrow(() -> smsServiceImpl.notificarPrevisao(mensagem));
	}
	
	@Test
	void deveFalharAoNotificarPrevisao_ComNumeroRejeitado() {
	    // Arrange
	    Mensagem mensagem = new Mensagem("Lucas", NUMERO_INVALIDO, "2");

	    // Act & Assert
	    RuntimeException e = assertThrows(RuntimeException.class, () -> 
	        smsServiceImpl.notificarPrevisao(mensagem)
	    );

	    assertTrue(e.getMessage().contains("Erro ao enviar SMS"));
	}
}
