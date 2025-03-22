package com.analise.credito;

import com.analise.credito.domain.Proposta;
import com.analise.credito.service.analiseCredito.AnaliseCreditoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MsAnaliseDeCreditoApplication {
	public static void main(String[] args) {
		SpringApplication.run(MsAnaliseDeCreditoApplication.class, args);
	}
}
