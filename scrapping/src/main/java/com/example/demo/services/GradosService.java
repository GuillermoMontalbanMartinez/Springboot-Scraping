package com.example.demo.services;

import com.example.demo.dto.*;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component("gradosService")
public class GradosService {
    public List<GradosDto> retrieveGrados() {

        List<GradosDto> gradosList = new ArrayList<>();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.webkit().launch();
        Page page = browser.newPage();
        // (1) Cargamos la página de grados la Universidad de Almería.
        page.navigate("https://www.ual.es/estudios/grados"); 
        // (2) Esperamos a que se cargue el selector del último de los grados
        page.waitForSelector(
                "body > div > div > div.container.main > div > section > div:nth-child(2) > div:nth-child(17) > div:nth-child(2) > div:nth-child(9) > div > ul > li:nth-child(2) > a > span");

        // (3) Traemos el HTML de la página y se lo pasamos a Jsoup para extraer los datos.
        Document webPage = Jsoup.parse(page.content());

        // (4) Seleccionamos los grados.
        Elements grados = webPage.select(".sinvinetas > li > a");

        for (Element grado : grados) {
            if (grado == null)
                continue;
            // (5) Obtenemos el nombre del grado.
            Element nombrElement = grado.selectFirst(".ng-binding");
            if (nombrElement == null)
                continue;
            String nombre = nombrElement.text();
            // (6) Obtenemos el código del grado.
            String codigo = grado.attr("href").replace("/estudios/grados/presentacion/", "");
            // (7) Creamos un objeto GradosDto con los datos del grado.
            gradosList.add(new GradosDto(nombre, codigo));
        }

        return gradosList;
    }
}