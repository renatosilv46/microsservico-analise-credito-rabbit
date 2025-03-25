package com.analise.credito;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class MsAnaliseDeCreditoApplication {
	public static void main(String[] args) {
		SpringApplication.run(MsAnaliseDeCreditoApplication.class, args);
		log.info("Servidor inicializado com sucesso na porta: 8080");
	}
}
