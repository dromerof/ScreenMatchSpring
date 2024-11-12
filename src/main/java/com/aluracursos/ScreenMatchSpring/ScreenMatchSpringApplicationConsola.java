/*
package com.aluracursos.ScreenMatchSpring;

import com.aluracursos.ScreenMatchSpring.principal.Principal;
import com.aluracursos.ScreenMatchSpring.repository.SerieRepository;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenMatchSpringApplicationConsola implements CommandLineRunner {

    @Autowired
    private SerieRepository repository;
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.configure().load();
        dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));

        SpringApplication.run(ScreenMatchSpringApplicationConsola.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Principal app = new Principal(repository);
        app.muestraMenu();
    }
}
*/
