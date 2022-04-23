package com.example.demo.controller;

import com.example.demo.dto.CovidDataDto;
import com.example.demo.services.CovidDataService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


// (1) Anotación de controlador REST.
@RestController 
// (2) Anotación de mapeo de petición en la URL /covid.
@RequestMapping("/covid") 
public class CovidDataController {
    @Autowired
    // (3) Inyección de dependencia de la clase CovidDataService.
    private CovidDataService covidDataService;

    // (4) Mapeo de petición GET a la URL /covid/data.
    @GetMapping("data")
    public ResponseEntity<List<CovidDataDto>> getCovidData() {
        // (5) Devuelve una lista de objetos CovidDataDto con los datos del scraping realizados por el servicio.
        return new ResponseEntity<List<CovidDataDto>>(covidDataService.retrieveCovidData(),
            HttpStatus.OK);
    }
}