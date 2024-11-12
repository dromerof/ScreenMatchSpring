package com.aluracursos.ScreenMatchSpring.service;

import com.aluracursos.ScreenMatchSpring.dto.SerieDTO;
import com.aluracursos.ScreenMatchSpring.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SerieService {

    @Autowired
    private SerieRepository repository;

    public List<SerieDTO> obtenerTodaslasSeries() {
        return repository.findAll().stream()
                .map(s -> new SerieDTO(s.getTitulo(), s.getLanzamiento(),
                        s.getGenero(), s.getTotalDeTemporadas(), s.getEvaluaciones(),
                        s.getActores(), s.getPoster(), s.getSinopsis()))
                .collect(Collectors.toList());
    }
}
