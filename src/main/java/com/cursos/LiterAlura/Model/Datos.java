package com.cursos.LiterAlura.Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Embeddable;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
@Embeddable
public record Datos(
        @JsonAlias ("count") Integer total,
        @JsonAlias ("next") String siguiente,
        @JsonAlias ("previous") String anterior,
        @JsonAlias("results") List<DatosLibros> resultados)
{
}
