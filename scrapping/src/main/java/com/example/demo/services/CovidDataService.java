package com.example.demo.services;


import com.example.demo.dto.CovidDataDto;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;



@Component("covidDataService") // (1) Anotación de componente.
public class CovidDataService {
    public List<CovidDataDto> retrieveCovidData() {

        List<CovidDataDto> covidData = new ArrayList<>();

        try {
            // (2) Conexión con la página web
            Document webPage = Jsoup.connect("https://en.wikipedia.org/wiki/COVID-19_pandemic_by_country_and_territory")
                    .get(); 
            // (3) Selección del elemento tbody de la capa con id covid-19-cases-deaths-and-rates-by-location.
            Element tbody = webPage.getElementById("covid-19-cases-deaths-and-rates-by-location").getElementsByTag("tbody").get(0); 
            
            // (4) Selección de los elementos hijos de la etiqueta tbody que empiezan en la posición 2.
            List<Element> rows = tbody.children().subList(2, tbody.children().size()); 

            for (Element row : rows) { // (5) Bucle for para recorrer los elementos hijos de la etiqueta tbody, es decir, cada país.

                Elements ths = row.getElementsByTag("th");
                if(ths.isEmpty())   // (6) Si el elemento th está vacío, se salta a la siguiente iteración. No es un país.
                    continue;

                String country = ths.get(0).text(); // (7) Selección del elemento th que contiene el nombre del país.
                Elements tds = row.getElementsByTag("td");

                if (tds.size() < 3) // (8) Si el número de elementos td es menor que 3, se salta a la siguiente iteración. Pertenece al pie de la tabla.
                    continue;

                // (9) Selección del elemento td que contiene el número de casos, muertes o recuperados.
                Integer cases = toIntOrNull(tds.get(1).text());
                Integer deaths = toIntOrNull(tds.get(2).text());
                Integer recovered = toIntOrNull(tds.get(3).text());

                // (10) Se crea un nuevo objeto CovidDataDto con los datos del país.
                covidData.add(new CovidDataDto(country, cases, deaths, recovered));
            }

            return covidData;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Integer toIntOrNull(String replace) {
        try {
            return Integer.parseInt(replace.replace(",", ""));
        } catch (NumberFormatException e) {
            return null;
        }
    }
}