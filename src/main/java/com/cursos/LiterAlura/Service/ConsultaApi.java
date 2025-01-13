package com.cursos.LiterAlura.Service;
import com.cursos.LiterAlura.Model.DatosLibros;
import com.cursos.LiterAlura.Model.DatosResultados;
import com.cursos.LiterAlura.Model.Libro;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class ConsultaApi {
    private final String urlBase = "https://gutendex.com/books"; // URL base de la API
    private final IConvierteDatos convierteDatos; // Dependencia para convertir JSON

    public ConsultaApi(IConvierteDatos convierteDatos) {
        this.convierteDatos = convierteDatos; // Inyección de dependencia
    }


    // Método para obtener los datos desde la API
    public DatosResultados obtenerDatos(String url) {
        // Realizar la petición HTTP
       String json = realizarPeticion(url);

        // Validar que el JSON no esté vacío o nulo
        if (json == null || json.isEmpty()) {
            throw new RuntimeException("El JSON recibido está vacío o nulo.");
        }

        // Convertir el JSON en un objeto de tipo DatosResultados
        try {
            return convierteDatos.obtenerDatos(json, DatosResultados.class);
        } catch (Exception e) {
            throw new RuntimeException("Error al convertir JSON a la clase DatosResultados: " + e.getMessage(), e);
        }

    }

    // Método privado para realizar la petición HTTP
    public String realizarPeticion(String url) {
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS) // Seguir redirecciones automáticamente
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Verificar el código de estado HTTP
            if (response.statusCode() != 200) {
                throw new RuntimeException("Error en la petición HTTP: Código de estado " + response.statusCode());
            }

            return response.body(); // Retorna el cuerpo de la respuesta (JSON)

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error al realizar la solicitud a la API", e);
        }

    }


}
