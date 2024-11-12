package com.aluracursos.ScreenMatchSpring.controlller;

import com.aluracursos.ScreenMatchSpring.model.Serie;
import com.aluracursos.ScreenMatchSpring.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SerieController {
    @Autowired
    private SerieRepository repository;

    @GetMapping("/series")
    public List<Serie> obtenerTodaslasSeries(){
        return repository.findAll();
    }
}
