package com.aluracursos.ScreenMatchSpring.controlller;

import com.aluracursos.ScreenMatchSpring.dto.SerieDTO;
import com.aluracursos.ScreenMatchSpring.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class SerieController {

    @Autowired
    private SerieService service;

    @GetMapping("/series")
    public List<SerieDTO> obtenerTodaslasSeries(){
        return service.obtenerTodaslasSeries();
    }
}
