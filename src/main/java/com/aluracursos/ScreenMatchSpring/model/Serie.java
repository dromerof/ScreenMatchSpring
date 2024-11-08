package com.aluracursos.ScreenMatchSpring.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.OptionalDouble;

@Entity
@Table(name = "series")

public class Serie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    private String lanzamiento;
    @Enumerated(EnumType.STRING)
    private Categoria genero;
    private Integer totalDeTemporadas;
    private Double evaluaciones;
    private String actores;
    private String poster;
    private String sinopsis;
    @OneToMany(mappedBy = "serie")
    private List<Episodio> episodios;

    public Serie() {
    }

    public Serie(DatosSerie datosSerie) {
        this.titulo = datosSerie.titulo();
        this.lanzamiento = datosSerie.lanzamiento();
        this.genero = Categoria.fromString(datosSerie.genero().split(",")[0]);
        this.totalDeTemporadas = datosSerie.totalDeTemporadas();
        this.evaluaciones = OptionalDouble.of(Double.valueOf(datosSerie.evaluaciones())).orElse(0);
        this.actores = datosSerie.actores();
        this.poster = datosSerie.poster();
        this.sinopsis = datosSerie.sinopsis();
    }

    @Override
    public String toString() {
        return String.format(
                "genero = %s%n" +
                        "titulo = '%s'%n" +
                        "lanzamiento = '%s'%n" +
                        "totalDeTemporadas = %d%n" +
                        "evaluaciones = %.2f%n" +
                        "actores = '%s'%n" +
                        "poster = '%s'%n" +
                        "sinopsis = '%s'%n",
                genero,
                titulo,
                lanzamiento,
                totalDeTemporadas,
                evaluaciones,
                actores,
                poster,
                sinopsis
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Episodio> getEpisodios() {
        return episodios;
    }

    public void setEpisodios(List<Episodio> episodios) {
        this.episodios = episodios;
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




