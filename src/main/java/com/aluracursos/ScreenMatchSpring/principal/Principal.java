package com.aluracursos.ScreenMatchSpring.principal;

import com.aluracursos.ScreenMatchSpring.model.DatosSerie;
import com.aluracursos.ScreenMatchSpring.model.DatosTemporada;
import com.aluracursos.ScreenMatchSpring.model.Serie;
import com.aluracursos.ScreenMatchSpring.repository.SerieRepository;
import com.aluracursos.ScreenMatchSpring.service.ConsumoAPI;
import com.aluracursos.ScreenMatchSpring.service.ConvierteDatos;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Principal {
    private final Scanner teclado = new Scanner(System.in);
    private final ConsumoAPI consumoApi = new ConsumoAPI();
    private final ConvierteDatos conversor = new ConvierteDatos();
    private final List<DatosSerie> datosSeries = new ArrayList<>();
    private final Dotenv dotenv = Dotenv.load();
    private SerieRepository repository;

    public Principal(SerieRepository repository) {
        this.repository = repository;
    }


    //Metodo para mostrar el menu
    public void muestraMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                     1 - Buscar series
                     2 - Buscar episodios
                     3 - Mostrar series buscadas
                     0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarSerieWeb();
                    break;
                case 2:
                    buscarEpisodioPorSerie();
                    break;

                case 3:
                    mostrarSeriesBuscadas();
                    break;

                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }

    private DatosSerie getDatosSerie() {
        System.out.println("Escribe el nombre de la serie que deseas buscar");
        var nombreSerie = teclado.nextLine();
        var json = consumoApi.obtenerDatos(dotenv.get("API_URL") + nombreSerie.replace(" ", "+") + dotenv.get("API_KEY"));
        System.out.println(json);
        DatosSerie datos = conversor.obtenerDatos(json, DatosSerie.class);
        return datos;
    }

    private void buscarEpisodioPorSerie() {
        DatosSerie datosSerie = getDatosSerie();
        List<DatosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i <= datosSerie.totalDeTemporadas(); i++) {
            var json = consumoApi.obtenerDatos(dotenv.get("API_URL") + datosSerie.titulo().replace(" ", "+") + "&season=" + i + dotenv.get("API_KEY"));
            DatosTemporada datosTemporada = conversor.obtenerDatos(json, DatosTemporada.class);
            temporadas.add(datosTemporada);
        }
        temporadas.forEach(System.out::println);
    }

    private void buscarSerieWeb() {
        DatosSerie datos = getDatosSerie();
        Serie serie = new Serie(datos);
        repository.save(serie);
        System.out.println(datos);
    }

    private void mostrarSeriesBuscadas(){
        List<Serie> series = new ArrayList<>();
        series = datosSeries.stream()
                .map(d -> new Serie(d))
                .collect(Collectors.toList());

        series.stream()
                .sorted(Comparator.comparing(Serie::getGenero))
                .forEach(System.out::println);

    }

}

