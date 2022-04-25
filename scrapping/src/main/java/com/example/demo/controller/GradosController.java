package com.example.demo.controller;

import java.util.List;

import com.example.demo.dto.GradosDto;
import com.example.demo.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
// (1) Mapeamos la ruta /grados/data para obtener los datos de los grados.
@RequestMapping("/grados")
public class GradosController {

    @Autowired
    // (2) Inyectamos el servicio de grados.
    private GradosService gradosService;

    // (3) Devolvemos la lista de grados.
    @GetMapping("data")
    public ResponseEntity<List<GradosDto>> getGrados() {
        return new ResponseEntity<List<GradosDto>>(gradosService.retrieveGrados(),
                HttpStatus.OK);
    }
}