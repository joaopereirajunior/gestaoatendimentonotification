package br.com.fiap.gestaoatendimentonotification.controller;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import io.restassured.RestAssured;

@AutoConfigureTestDatabase
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SmsControllerIT {

	@LocalServerPort
	private int port;

	@BeforeEach
	public void setup() {
	    RestAssured.port = port;
	    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
	}
}
