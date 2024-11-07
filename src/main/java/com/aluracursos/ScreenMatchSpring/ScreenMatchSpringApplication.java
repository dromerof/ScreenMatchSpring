package com.aluracursos.ScreenMatchSpring;

import com.aluracursos.ScreenMatchSpring.principal.Principal;
import com.aluracursos.ScreenMatchSpring.repository.SerieRepository;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenMatchSpringApplication implements CommandLineRunner {

    @Autowired
    private SerieRepository repository;
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.configure().load(); // Carga el archivo .env

        // Establecer las variables del .env como propiedades del sistema para Spring Boot
        System.setProperty("DB_URL", dotenv.get("DB_URL"));
        System.setProperty("DB_USER", dotenv.get("DB_USER"));
        System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
        System.setProperty("API_KEY", dotenv.get("API_KEY"));
        System.setProperty("API_URL", dotenv.get("API_URL"));

        SpringApplication.run(ScreenMatchSpringApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Principal app = new Principal(repository);
        app.muestraMenu();
    }
}
