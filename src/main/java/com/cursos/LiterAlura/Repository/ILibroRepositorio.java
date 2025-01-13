package com.cursos.LiterAlura.Repository;

import com.cursos.LiterAlura.Model.Autor;
import com.cursos.LiterAlura.Model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ILibroRepositorio extends JpaRepository <Libro, Long>
{
    // Método para verificar si ya existe un libro con el mismo título
    Optional<Libro> findByTitulo(String titulo);


    boolean existsByTituloAndAutores_Nombre(String titulo, String nombreAutor);


}
