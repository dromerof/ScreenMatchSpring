package com.aluracursos.ScreenMatchSpring.principal;

import com.aluracursos.ScreenMatchSpring.model.DatosEpisodio;
import com.aluracursos.ScreenMatchSpring.model.DatosSerie;
import com.aluracursos.ScreenMatchSpring.model.DatosTemporada;
import com.aluracursos.ScreenMatchSpring.model.Episodio;
import com.aluracursos.ScreenMatchSpring.service.ConsumoAPI;
import com.aluracursos.ScreenMatchSpring.service.ConvierteDatos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private final Scanner teclado = new Scanner(System.in);
    private final ConsumoAPI consumoApi = new ConsumoAPI();
    private final String API_URL = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=4fc7c187";
    private final ConvierteDatos conversor = new ConvierteDatos();


    //Metodo para mostrar el menu
    public void muestraMenu() {
        System.out.println("Por favor escribe el nombre de la serie que deseas buscar");

        var nombreSerie = teclado.nextLine();
        var json = consumoApi.obtenerDatos(API_URL + nombreSerie.replace(" ", "+") + API_KEY);
        var datos = conversor.obtenerDatos(json, DatosSerie.class);
        System.out.println(datos);

        //Busca los datos de todas las temporadas
        List<DatosTemporada> temporadas = new ArrayList<>();
        for (int i = 1; i <= datos.totalDeTemporadas(); i++) {
            String temporadaUrl = String.format(API_URL + nombreSerie.replace(" ", "+") + "&Season=" + i + API_KEY);
            json = consumoApi.obtenerDatos(temporadaUrl);
            var datosTemporada = conversor.obtenerDatos(json, DatosTemporada.class);
            temporadas.add(datosTemporada);
        }

        //Mostrar solo el título de los episodios para las temporadas
       /* for (int i = 0; i < datos.totalDeTemporadas(); i++) {
            List<DatosEpisodio> episodiosTemporada = temporadas.get(i).episodios();
            for (int j = 0; j < episodiosTemporada.size(); j++) {
                System.out.println(episodiosTemporada.get(j).titulo());
            }
        }*/

        //Mostrar usando funciones Lambda
/*
        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));
*/

        //Convertir todas las informaciones a una lista del tipo DatosEpisodios
        List<DatosEpisodio> datosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList());





        //Top 5 episodios
        /*System.out.println("Top 5 episodios");
        datosEpisodios.stream()
                .filter(e -> !e.evaluacion().equalsIgnoreCase("N/A"))
                .peek(e -> System.out.println("Primer filtro (N/A)" + e) )
                .sorted(Comparator.comparing(DatosEpisodio::evaluacion).reversed())
                .peek(e -> System.out.println("Segundo filtro para ordenar (M>m)" + e) )
                .map(e -> e.titulo().toUpperCase())
                .peek(e -> System.out.println("Tercer filtro para poner en mayúsculas(M>n)" + e) )
                .limit(5)
                .peek(e -> System.out.println("Tercer filtro para poner un limite ()" + e) )
                .forEach(System.out::println);*/

        //Convirtiendo los datos a una lista del tipo episodio
        List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d -> new Episodio(t.numero(), d)))
                .collect(Collectors.toList());

        /*episodios.forEach(System.out::println);*/

        //Busqueda de episodios a partir de x año
      /*  System.out.println("Por favor indidca el año desde el cual desear ver los episodios ");

        var fecha = teclado.nextLine();
        teclado.nextLine();

        LocalDate fechaBusqueda = LocalDate.of(Integer.parseInt(fecha),1,1);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");*/
        /*episodios.stream()
                .filter(e -> e.getFechaDeLanzamiento() != null && e.getFechaDeLanzamiento().isAfter(fechaBusqueda))
                .forEach(e -> System.out.println(
                        "Temporada " + e.getTemporada() +
                        "Episodio " + e.getTitulo() +
                        "Fecha de lanzamiento " + e.getFechaDeLanzamiento().format(dtf)
                ));*/

        //Busca episodio por comienzo de titulo
        System.out.println("Por favor escribe el comienzo del titulo del episodio que deseas encontrar");
        var comienzoTitulo = teclado.nextLine();
        final Optional<Episodio> episodioBuscado = episodios.stream()
                .filter(e -> e.getTitulo().toUpperCase().contains(comienzoTitulo.toUpperCase()))
                .findFirst();
        if (episodioBuscado.isPresent()){
            System.out.println(" Episodio encontrado");
            System.out.println(" Los datos son: " + episodioBuscado.get());
        }else {
            System.out.println("Episodio no encontrado ");
        }
    }
}
