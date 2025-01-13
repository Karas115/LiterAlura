package com.cursos.LiterAlura.Repository;

import com.cursos.LiterAlura.Model.Autor;
import com.cursos.LiterAlura.Model.Idioma;
import com.cursos.LiterAlura.Model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAutorRepositorio extends JpaRepository <Autor, Long>
{
    Optional<Autor> findByNombre(String nombre); // Busca por nombre del autor
}
