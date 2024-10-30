package com.aluracursos.ScreenMatchSpring;

import com.aluracursos.ScreenMatchSpring.principal.Principal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ScreenMatchSpringApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ScreenMatchSpringApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Principal app = new Principal();
        app.muestraMenu();


    }
}
