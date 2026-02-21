package com.itb.tcc.mif3an.pizzariadomario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PizzariadomarioApplication {

	public static void main(String[] args) {

        SpringApplication.run(PizzariadomarioApplication.class, args);

        System.out.println("Servidor respondendo da porta 8080");

	}

}
