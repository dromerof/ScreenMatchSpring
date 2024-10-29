package com.aluracursos.ScreenMatchSpring.principal;

import com.aluracursos.ScreenMatchSpring.model.DatosSerie;
import com.aluracursos.ScreenMatchSpring.model.DatosTemporada;
import com.aluracursos.ScreenMatchSpring.service.ConsumoAPI;
import com.aluracursos.ScreenMatchSpring.service.ConvierteDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

        //Busca los datos de todas las temporadas
        List<DatosTemporada> temporadas = new ArrayList<>();
        for (int i = 1; i <= datos.totalDeTemporadas(); i++) {
            String temporadaUrl = String.format(API_URL + nombreSerie.replace(" ", "+") + "&Season=" + i + API_KEY);
            json = consumoApi.obtenerDatos(temporadaUrl);
            DatosTemporada datosTemporada = conversor.obtenerDatos(json, DatosTemporada.class);
            temporadas.add(datosTemporada);
        }
        temporadas.forEach(System.out::println);

    }
}
