package com.cursos.LiterAlura.Service;

import com.cursos.LiterAlura.Service.IConvierteDatos;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class ConvierteDatos implements IConvierteDatos {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        // Configuración explícita de módulos
        objectMapper.registerModule(new JavaTimeModule()); // Soporte para java.time
        objectMapper.registerModule(new Jdk8Module());     // Soporte para Optional
    }

    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) {
        try {
            return objectMapper.readValue(json, clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error al convertir JSON a la clase " + clase.getSimpleName() + ": " + e.getMessage(), e);
        }
    }
}

