package com.cursos.LiterAlura.Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.Embeddable;
import jakarta.persistence.criteria.CriteriaBuilder;

@Embeddable
public record DatosAutor(
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") Integer nacimiento,
        @JsonAlias("death_year")Integer muerte

        ) {
}
