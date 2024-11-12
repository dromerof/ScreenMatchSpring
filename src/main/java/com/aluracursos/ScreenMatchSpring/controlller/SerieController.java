package com.aluracursos.ScreenMatchSpring.controlller;

import com.aluracursos.ScreenMatchSpring.dto.SerieDTO;
import com.aluracursos.ScreenMatchSpring.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/series")
public class SerieController {

    @Autowired
    private SerieService service;

    @GetMapping()
    public List<SerieDTO> obtenerTodaslasSeries() {
        return service.obtenerTodaslasSeries();
    }

    @GetMapping("/top5")
    public List<SerieDTO> obtenerTop5() {
        return service.obtenerTop5();
    }

    @GetMapping("/lanzamientos")
    public List<SerieDTO> obtenerSeriesRecientes() {
        return service.obtenerSeriesRecientes();
    }

}
