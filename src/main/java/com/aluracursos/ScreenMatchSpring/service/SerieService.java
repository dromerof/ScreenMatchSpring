package com.aluracursos.ScreenMatchSpring.service;

import com.aluracursos.ScreenMatchSpring.dto.EpisodioDTO;
import com.aluracursos.ScreenMatchSpring.dto.SerieDTO;
import com.aluracursos.ScreenMatchSpring.model.Categoria;
import com.aluracursos.ScreenMatchSpring.model.Serie;
import com.aluracursos.ScreenMatchSpring.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SerieService {

    @Autowired
    private SerieRepository repository;

    public List<SerieDTO> obtenerTodaslasSeries() {
        return convierteDatos(repository.findAll());
    }

    public List<SerieDTO> obtenerTop5() {
        return convierteDatos(repository.findTop5ByOrderByEvaluacionesDesc());
    }

    public List<SerieDTO> obtenerSeriesRecientes() {
        return convierteDatos(repository.seriesRecientes());
    }

    public List<SerieDTO> convierteDatos(List<Serie> serie) {

        return serie.stream()
                .map(s -> new SerieDTO(s.getId(), s.getTitulo(), s.getLanzamiento(),
                        s.getGenero(), s.getTotalDeTemporadas(), s.getEvaluaciones(),
                        s.getActores(), s.getPoster(), s.getSinopsis()))
                .collect(Collectors.toList());
    }

    public SerieDTO obtenerPorId(Long id) {
        Optional<Serie> serie = repository.findById(id);
        if (serie.isPresent()) {
            Serie s = serie.get();
            return new SerieDTO(s.getId(), s.getTitulo(), s.getLanzamiento(),
                    s.getGenero(), s.getTotalDeTemporadas(), s.getEvaluaciones(),
                    s.getActores(), s.getPoster(), s.getSinopsis());
        }
        return null;
    }

    public List<EpisodioDTO> obtenerTodaslasTemporadas(Long id) {
        Optional<Serie> serie = repository.findById(id);
        if (serie.isPresent()) {
            Serie s = serie.get();
            return s.getEpisodios().stream()
                    .map(e -> new EpisodioDTO(e.getTemporada(), e.getTitulo(), e.getNumeroEpisodio()))
                    .collect(Collectors.toList());
        }
        return null;
    }

    public List<EpisodioDTO> obtenerTemporadasPorNumero(Long id, Long numeroTemporada) {
        return repository.obtenerTemporadasPorNumero(id, numeroTemporada).stream()
                .map(e -> new EpisodioDTO(e.getTemporada(), e.getTitulo(), e.getNumeroEpisodio()))
                .collect(Collectors.toList());

    }

    public List<SerieDTO> obtenerSeriesPorcategoria(String nombreGenero) {
        Categoria categoria = Categoria.fromEspanol(nombreGenero);
        return convierteDatos(repository.findByGenero(categoria));
    }
}
