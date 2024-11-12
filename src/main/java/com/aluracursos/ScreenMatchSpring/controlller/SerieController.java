package com.aluracursos.ScreenMatchSpring.controlller;

import com.aluracursos.ScreenMatchSpring.dto.SerieDTO;
import com.aluracursos.ScreenMatchSpring.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SerieController {
    @Autowired
    private SerieRepository repository;

    @GetMapping("/series")
    public List<SerieDTO> obtenerTodaslasSeries(){
        return repository.findAll().stream()
                .map(s -> new SerieDTO(s.getTitulo(), s.getLanzamiento(),
                        s.getGenero(), s.getTotalDeTemporadas(), s.getEvaluaciones(),
                        s.getActores(), s.getPoster(), s.getSinopsis()))
                .collect(Collectors.toList());
    }
}
