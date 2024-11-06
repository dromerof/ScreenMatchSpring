package com.aluracursos.ScreenMatchSpring.model;

import java.util.OptionalDouble;

public class Serie {
    private String titulo;
    private String lanzamiento;
    private Categoria genero;
    private Integer totalDeTemporadas;
    private Double evaluaciones;
    private String actores;
    private String poster;
    private String sinopsis;

    public Serie(DatosSerie datosSerie){
        this.titulo = datosSerie.titulo();
        this.lanzamiento = datosSerie.lanzamiento();
        this.genero = Categoria.fromString(datosSerie.genero().split(";")[0]);
        this.totalDeTemporadas = datosSerie.totalDeTemporadas();
        this.evaluaciones = OptionalDouble.of(Double.valueOf(datosSerie.evaluaciones())).orElse(0);
        this.actores = datosSerie.actores();
        this.poster = datosSerie.poster();
        this.sinopsis = datosSerie.sinopsis();
    }

    @Override
    public String toString() {
        return
                "titulo='" + titulo + '\'' +
                ", lanzamiento='" + lanzamiento + '\'' +
                ", genero=" + genero +
                ", totalDeTemporadas=" + totalDeTemporadas +
                ", evaluaciones=" + evaluaciones +
                ", actores='" + actores + '\'' +
                ", poster='" + poster + '\'' +
                ", sinopsis='" + sinopsis + '\'';
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getLanzamiento() {
        return lanzamiento;
    }

    public void setLanzamiento(String lanzamiento) {
        this.lanzamiento = lanzamiento;
    }

    public Categoria getGenero() {
        return genero;
    }

    public void setGenero(Categoria genero) {
        this.genero = genero;
    }

    public Integer getTotalDeTemporadas() {
        return totalDeTemporadas;
    }

    public void setTotalDeTemporadas(Integer totalDeTemporadas) {
        this.totalDeTemporadas = totalDeTemporadas;
    }

    public Double getEvaluaciones() {
        return evaluaciones;
    }

    public void setEvaluaciones(Double evaluaciones) {
        this.evaluaciones = evaluaciones;
    }

    public String getActores() {
        return actores;
    }

    public void setActores(String actores) {
        this.actores = actores;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }
}




