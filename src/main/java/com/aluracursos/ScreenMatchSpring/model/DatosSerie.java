package com.aluracursos.ScreenMatchSpring.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosSerie(
        @JsonAlias("Title") String titulo,
        @JsonAlias("Year") String lanzamiento,
        @JsonAlias("Genre") String genero,
        @JsonAlias("totalSeasons") Integer totalDeTemporadas,
        @JsonAlias("imdbRating") String evaluaciones,
        @JsonAlias("Actors") String actores,
        @JsonAlias("Poster") String poster,
        @JsonAlias("Plot") String sinopsis

) {
    @Override
    public String toString() {
        return String.format(
                "titulo = '%s'%n" +
                        "lanzamiento = '%s'%n" +
                        "genero = '%s'%n" +
                        "totalDeTemporadas = %d%n" +
                        "evaluaciones = '%s'%n" +
                        "actores = '%s'%n" +
                        "poster = '%s'%n" +
                        "sinopsis = '%s'%n",
                titulo,
                lanzamiento,
                genero,
                totalDeTemporadas,
                evaluaciones,
                actores,
                poster,
                sinopsis
        );
    }

}
