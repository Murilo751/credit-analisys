package com.example.creditanalisys;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(
                title = "Análise de Crédito API",
                version = "1.0",
                description = "API para o sistema de análise de crédito, que permite criar, atualizar e consultar análises e solicitações de crédito.",
                contact = @Contact(
                        name = "Analise-De-Credito",
                        email = "murilo.matos2005@gmail.com",
                        url = "https://www.google.com")
        )
)

@SpringBootApplication
public class CreditAnalisysApplication {

    public static void main(String[] args) {
        SpringApplication.run(CreditAnalisysApplication.class, args);
    }

}
