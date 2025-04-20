package br.com.fiap.smsservice.controller;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import br.com.fiap.smsservice.model.dto.SmsRequestDTO;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SmsControllerIT {

	@LocalServerPort
	private int port;
	
	private static final String NUMERO_SUCESSO = "+15005550006";
	private static final String NUMERO_INVALIDO = "+15005550001";

	@BeforeEach
	public void setup() {
	    RestAssured.port = port;
	    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
	}
	
	@Test
	void deveNotificarEntradaComSucesso() {
	    // Arrange
	    SmsRequestDTO request = new SmsRequestDTO("Lucas", NUMERO_SUCESSO, "1");

	    // Act & Assert
	    given().filter(new AllureRestAssured()).contentType(MediaType.APPLICATION_JSON_VALUE)
	        .contentType(MediaType.APPLICATION_JSON_VALUE)
	        .body(request)
	        .when().post("/api/notificacoes/entrada")
	        .then().statusCode(HttpStatus.OK.value());
	}
	
	@Test
	void deveNotificarEntradaComFalha() {
	    // Arrange
	    SmsRequestDTO request = new SmsRequestDTO("Lucas", NUMERO_INVALIDO, "1");

	    // Act & Assert
	    given().filter(new AllureRestAssured()).contentType(MediaType.APPLICATION_JSON_VALUE)
	        .contentType(MediaType.APPLICATION_JSON_VALUE)
	        .body(request)
	        .when().post("/api/notificacoes/entrada")
	        .then().statusCode(HttpStatus.BAD_REQUEST.value());
	}
	
	@Test
	void deveNotificarSaidaComSucesso() {
	    // Arrange
	    SmsRequestDTO request = new SmsRequestDTO("Lucas", NUMERO_SUCESSO, "1");

	    // Act & Assert
	    given().filter(new AllureRestAssured())
	        .contentType(MediaType.APPLICATION_JSON_VALUE)
	        .body(request)
	        .when().post("/api/notificacoes/saida")
	        .then().statusCode(HttpStatus.OK.value());
	}
	
	@Test
	void deveNotificarSaidaComFalha() {
	    // Arrange
	    SmsRequestDTO request = new SmsRequestDTO("Lucas", NUMERO_INVALIDO, "1");

	    // Act & Assert
	    given().filter(new AllureRestAssured())
	        .contentType(MediaType.APPLICATION_JSON_VALUE)
	        .body(request)
	        .when().post("/api/notificacoes/saida")
	        .then().statusCode(HttpStatus.BAD_REQUEST.value());
	}
	
	@Test
	void deveNotificarPrevisaoComSucesso() {
	    // Arrange
	    SmsRequestDTO request = new SmsRequestDTO("Lucas", NUMERO_SUCESSO, "2");

	    // Act & Assert
	    given().filter(new AllureRestAssured())
	        .contentType(MediaType.APPLICATION_JSON_VALUE)
	        .body(request)
	        .when().post("/api/notificacoes/previsao")
	        .then().statusCode(HttpStatus.OK.value());
	}
	
	@Test
	void deveNotificarPrevisaoComFalha() {
	    // Arrange
	    SmsRequestDTO request = new SmsRequestDTO("Lucas", NUMERO_INVALIDO, "2");

	    // Act & Assert
	    given().filter(new AllureRestAssured())
	        .contentType(MediaType.APPLICATION_JSON_VALUE)
	        .body(request)
	        .when().post("/api/notificacoes/previsao")
	        .then().statusCode(HttpStatus.BAD_REQUEST.value());
	}
}
