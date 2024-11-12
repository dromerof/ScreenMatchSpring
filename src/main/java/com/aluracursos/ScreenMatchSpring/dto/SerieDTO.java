package com.aluracursos.ScreenMatchSpring.dto;

import com.aluracursos.ScreenMatchSpring.model.Categoria;

public record SerieDTO(
        String titulo,
        String lanzamiento,
        Categoria genero,
        Integer totalDeTemporadas,
        Double evaluaciones,
        String actores,
        String poster,
        String sinopsis
) {

}
