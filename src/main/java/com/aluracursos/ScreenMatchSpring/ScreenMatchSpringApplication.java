package com.aluracursos.ScreenMatchSpring;

import com.aluracursos.ScreenMatchSpring.model.DatosEpisodio;
import com.aluracursos.ScreenMatchSpring.model.DatosSerie;
import com.aluracursos.ScreenMatchSpring.service.ConsumoAPI;
import com.aluracursos.ScreenMatchSpring.service.ConvierteDatos;
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
        var consumoApi = new ConsumoAPI();
        var json = consumoApi.obtenerDatos("https://www.omdbapi.com/?t=Game+of+Thrones&apikey=4fc7c187");

/*
        var json = consumoApi.obtenerDatos("https://coffee.alexflipnote.dev/random.json");
*/
        System.out.println(json);
        ConvierteDatos conversor = new ConvierteDatos();
        var datos = conversor.obtenerDatos(json, DatosSerie.class);
        System.out.println(datos);
        json = consumoApi.obtenerDatos("https://www.omdbapi.com/?t=Game+of+Thrones&Season=1&episode=1&apikey=4fc7c187");
        DatosEpisodio episodio = conversor.obtenerDatos(json, DatosEpisodio.class);
        System.out.println(episodio);

    }
}
