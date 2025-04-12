package br.com.fiap.smsservice.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;

import br.com.fiap.smsservice.model.Mensagem;
import br.com.fiap.smsservice.service.impl.SmsServiceImpl;

class SmsServiceTest {
    
    private SmsServiceImpl smsServiceImpl;

    private AutoCloseable openMocks;
    
	@BeforeEach
	void setup() throws Exception{
		openMocks = MockitoAnnotations.openMocks(this);
		smsServiceImpl = new SmsServiceImpl();
		
	    setPrivateField(smsServiceImpl, "twilioSid", "fakeSid");
	    setPrivateField(smsServiceImpl, "twilioKey", "fakeKey");
	    setPrivateField(smsServiceImpl, "twilioPhoneFrom", "+5500000000");
	}
	
	@AfterEach
	void teardown() throws Exception {
		openMocks.close();
	}
	
	@Test
	void deveNotificarEntrada() {
		
		// Arrange
        Mensagem mensagem = new Mensagem("Lucas", "+5511999999999", "2");
        String textoEsperado = "Olá Lucas, você se registrou em nossa fila de atendimento e se encontra na posição 2.";
        
        PhoneNumber to = new PhoneNumber(mensagem.getNumero());
        PhoneNumber from = new PhoneNumber("+5500000000");

        MessageCreator mockCreator = mock(MessageCreator.class);
        Message mockMessage = mock(Message.class);

        when(mockMessage.getSid()).thenReturn("SID_MOCKADO");

        try (MockedStatic<Message> mockedMessage = mockStatic(Message.class)) {
            mockedMessage.when(() -> Message.creator(to, from, textoEsperado)).thenReturn(mockCreator);
            when(mockCreator.create()).thenReturn(mockMessage);

            // Act
            smsServiceImpl.notificarEntrada(mensagem);

            // Assert
            mockedMessage.verify(() -> Message.creator(to, from, textoEsperado), times(1));
            verify(mockCreator, times(1)).create();
            verify(mockMessage, times(1)).getSid();
        }

	}

	@Test
	void deveNotificarSaida() {
		
		// Arrange
        Mensagem mensagem = new Mensagem("Lucas", "+5511999999999", "2");
        String textoEsperado = "Chegou a sua vez de ser atendido, você é o primeiro da fila!";
        
        PhoneNumber to = new PhoneNumber(mensagem.getNumero());
        PhoneNumber from = new PhoneNumber("+5500000000");

        MessageCreator mockCreator = mock(MessageCreator.class);
        Message mockMessage = mock(Message.class);

        when(mockMessage.getSid()).thenReturn("SID_MOCKADO");

        try (MockedStatic<Message> mockedMessage = mockStatic(Message.class)) {
            mockedMessage.when(() -> Message.creator(to, from, textoEsperado)).thenReturn(mockCreator);
            when(mockCreator.create()).thenReturn(mockMessage);

            // Act
            smsServiceImpl.notificarSaida(mensagem);

            // Assert
            mockedMessage.verify(() -> Message.creator(to, from, textoEsperado), times(1));
            verify(mockCreator, times(1)).create();
            verify(mockMessage, times(1)).getSid();
        }
	}

	@Test
	void deveNotificarPrevisao() {
		
		// Arrange
        Mensagem mensagem = new Mensagem("Lucas", "+5511999999999", "2");
        String textoEsperado = "Você se encontra na posição 2 da fila de atendimento, "
        		+ "o tempo estimado de previsão é 10 minutos.";
        
        PhoneNumber to = new PhoneNumber(mensagem.getNumero());
        PhoneNumber from = new PhoneNumber("+5500000000");

        MessageCreator mockCreator = mock(MessageCreator.class);
        Message mockMessage = mock(Message.class);

        when(mockMessage.getSid()).thenReturn("SID_MOCKADO");

        try (MockedStatic<Message> mockedMessage = mockStatic(Message.class)) {
            mockedMessage.when(() -> Message.creator(to, from, textoEsperado)).thenReturn(mockCreator);
            when(mockCreator.create()).thenReturn(mockMessage);

            // Act
            smsServiceImpl.notificarPrevisao(mensagem);

            // Assert
            mockedMessage.verify(() -> Message.creator(to, from, textoEsperado), times(1));
            verify(mockCreator, times(1)).create();
            verify(mockMessage, times(1)).getSid();
        }
	}

	private void setPrivateField(Object target, String fieldName, Object value) throws Exception {
	    Field field = target.getClass().getDeclaredField(fieldName);
	    field.setAccessible(true);
	    field.set(target, value);
	}
}
