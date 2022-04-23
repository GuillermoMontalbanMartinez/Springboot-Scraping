package com.example.demo.dto;

public class CovidDataDto {
    public String  country; // Nombre del pais
    public Integer cases; // Número de casos
    public Integer deaths; // Número de muertos
    public Integer recovered; // Número de recuperados

    public CovidDataDto(String country, Integer cases, Integer deaths, Integer recovered) {
        this.country   = country;
        this.cases     = cases;
        this.deaths    = deaths;
        this.recovered = recovered;
    }

    public CovidDataDto() {
    }
}