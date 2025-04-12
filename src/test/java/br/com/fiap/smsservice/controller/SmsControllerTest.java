package br.com.fiap.smsservice.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.fiap.smsservice.mapper.MensagemMapper;
import br.com.fiap.smsservice.model.Mensagem;
import br.com.fiap.smsservice.model.dto.SmsRequestDTO;
import br.com.fiap.smsservice.service.SmsService;

class SmsControllerTest {

	private MockMvc mockMvc;

	private AutoCloseable openMocks;
	
	@Mock
	private SmsService smsService;
	
	private SmsController smsController;
	
	private static final String NUMERO_SUCESSO = "+15005550006";
	
	@BeforeEach
	void setup() {
		openMocks = MockitoAnnotations.openMocks(this);
		
		MensagemMapper mensagemMapper = new MensagemMapper();
		smsController = new SmsController(smsService, mensagemMapper);

		mockMvc = MockMvcBuilders.standaloneSetup(smsController).build();
	}

	@AfterEach
	void teardown() throws Exception {
		openMocks.close();
	}
	
	@Test
	void deveNotificarEntradaComSucesso() throws Exception {
	    // Arrange
	    SmsRequestDTO request = new SmsRequestDTO("Lucas", NUMERO_SUCESSO, "1");
	    doNothing().when(smsService).notificarEntrada(any(Mensagem.class));
	    
	    // Act & Assert
	    mockMvc.perform(post("/api/notificacoes/entrada")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(asJsonString(request)))
	        	.andExpect(status().isOk());
	}
	
	@Test
	void deveNotificarSaidaComSucesso() throws Exception {
	    // Arrange
	    SmsRequestDTO request = new SmsRequestDTO("Lucas", NUMERO_SUCESSO, "1");
	    doNothing().when(smsService).notificarSaida(any(Mensagem.class));

	    
	    // Act & Assert
	    mockMvc.perform(post("/api/notificacoes/saida")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(asJsonString(request)))
	        	.andExpect(status().isOk());
	}
	
	@Test
	void deveNotificarPrevisaoComSucesso() throws Exception {
	    // Arrange
	    SmsRequestDTO request = new SmsRequestDTO("Lucas", NUMERO_SUCESSO, "2");
	    doNothing().when(smsService).notificarPrevisao(any(Mensagem.class));
	    
	    // Act & Assert
	    mockMvc.perform(post("/api/notificacoes/previsao")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(asJsonString(request)))
	        	.andExpect(status().isOk());
	}
	
	public static String asJsonString(final Object object) {
		try {
			return new ObjectMapper().writeValueAsString(object);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
}
