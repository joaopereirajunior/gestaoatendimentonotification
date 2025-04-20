#  Sistema de Notificação via SMS

Este projeto faz parte do *Tech Challenge - Hackaton Fase 5*, nele foi construido um Sistema de Notificação via SMS com sua construção baseada na estrutura *MVC*, utilizando tecnologias modernas como *Java*, *Spring Boot* e *Docker*, com foco na usabilidade e na escalabilidade. O sistema permite o envio de notificações SMS com informações relacionadas a posição da fila em que o paciente se encontra.

## Tecnologias Utilizadas

- **Java 17**: Versão de linguagem utilizada.
- **Spring Boot**: Framework para desenvolvimento de aplicações Java.
- **Swagger**: Para documentação e testes das APIs.
- **JUnit, AssertJ**: Para criação de testes unitários.
- **Twilio**: API externa para envio de mensagens SMS.

## Instruções para Acesso à Aplicação

A aplicação se encontra disponível no seguinte endereço:

URL LOCAL: [[http://localhost:8082/swagger-ui.html](http://localhost:8082/swagger-ui.html)]

## Para executar a aplicação via Docker, siga os comandos abaixo:

1. **Faça login no Docker:**
   ```bash
   docker login
    ```
2. **Construa a imagem a partir do diretório raiz do projeto:**
     ```bash
    docker build -t notification-app .
    ```
3. **Crie e inicie um container a partir da imagem criada com o seguinte comando**
     ```bash
    docker run -p 8082:8082 -d notification-app
    ```    
## Instruções para Execução dos Testes

- Comando para execução dos **Testes Unitários**:
   ```bash
    mvn test
    ```
- Comando para execução dos **Testes Integrados**:
   ```bash
    mvn test -P integration-test
    ```

## Documentação da API

A documentação da API é gerada automaticamente pelo Swagger. Você pode acessá-la inserindo /swagger-ui/index.html ao final da url ou no seguinte endereço após iniciar a aplicação localmente:

URL LOCAL: [[http://localhost:8082/swagger-ui.html](http://localhost:8082/swagger-ui.html)]

Consulte a documentação do Swagger UI para ver todos os endpoints disponíveis e detalhes sobre cada um deles.
