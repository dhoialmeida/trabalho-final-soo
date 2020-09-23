package br.unesp.grupo5.trabalhofinal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TrabalhofinalApplication {

    public static void main(String[] args) {
        System.out.println("Initlializing... Working Directory = " + System.getProperty("user.dir"));
        SpringApplication.run(TrabalhofinalApplication.class, args);
    }

}
