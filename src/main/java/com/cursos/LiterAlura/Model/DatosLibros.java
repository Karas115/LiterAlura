package com.cursos.LiterAlura.Model;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
@Embeddable
public record DatosLibros(
        //@JsonAlias("id")Integer id,
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List <Autor> autores,
        @JsonAlias("bookshelves") List <String> estanteria,
        @JsonAlias("languages") List<String> idiomas,
        @JsonAlias("download_count")Integer descargas

)
{
}
