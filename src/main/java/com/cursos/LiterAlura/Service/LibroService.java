package com.cursos.LiterAlura.Service;

import com.cursos.LiterAlura.Model.Autor;
import com.cursos.LiterAlura.Model.DatosAutor;
import com.cursos.LiterAlura.Model.Libro;
import com.cursos.LiterAlura.Repository.IAutorRepositorio;
import com.cursos.LiterAlura.Repository.ILibroRepositorio;
import com.cursos.LiterAlura.dto.AutorDTO;
import com.cursos.LiterAlura.dto.LibroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibroService
{
    private final ILibroRepositorio libroRepositorio;
    private final IAutorRepositorio autorRepositorio;

    @Autowired
    public LibroService(ILibroRepositorio libroRepositorio, IAutorRepositorio autorRepositorio) {
        this.libroRepositorio = libroRepositorio;
        this.autorRepositorio = autorRepositorio;
    }

    /**
     * Convierte una lista de DatosAutor a entidades Autor.

    private List<Autor> convertirDatosAutorAEntidades(List<DatosAutor> datosAutores) {
        if (datosAutores == null || datosAutores.isEmpty()) {
            return new ArrayList<>();
        }

        return datosAutores.stream()
                .map(datosAutor -> new Autor(datosAutor)) // Crear nuevas entidades Autor a partir de DatosAutor
                .collect(Collectors.toList());
    }*/
    private List<Autor> convertirDatosAutorAEntidades(List<DatosAutor> datosAutores) {
        if (datosAutores == null || datosAutores.isEmpty()) {
            return new ArrayList<>();
        }

        return datosAutores.stream()
                .map(datosAutor -> {
                    Autor autor = new Autor();
                    autor.setNombre(datosAutor.nombre());
                    autor.setNacimiento(datosAutor.nacimiento());
                    autor.setMuerte(datosAutor.muerte());
                    return autor;
                })
                .collect(Collectors.toList());
    }

    /**
     * Convierte una entidad Libro a un DTO de Libro.
     */
    public LibroDTO convertirLibroAEntidades(Libro libro) {
        // Convertir la lista de entidades Autor a DTO
        List<AutorDTO> autoresDTO = libro.getAutores().stream()
                .map(autor -> new AutorDTO(
                        autor.getNombre(),  // Usar getters
                        autor.getNacimiento(),
                        autor.getMuerte()
                ))
                .collect(Collectors.toList());

        // Retornar el DTO del libro
        return new LibroDTO(
                libro.getTitulo(),
                autoresDTO,
                libro.getIdiomas(),
                libro.getEstanteria(),
                libro.getDescargas()
        );
    }

    // Listar todos los libros y convertirlos a DTO.
    public List<LibroDTO> listarLibros() {
        return libroRepositorio.findAll().stream()
                .map(this::convertirLibroAEntidades) // Reutiliza el método de conversión
                .collect(Collectors.toList());
    }
    public boolean existeLibroPorTituloYAutor(String titulo, String nombreAutor) {
        return libroRepositorio.existsByTituloAndAutores_Nombre(titulo, nombreAutor);
    }
    public void guardarLibro(Libro libro) {
        if (libro.getAutores() != null) {
            libro.getAutores().forEach(autor -> autor.setLibro(libro));
        }

        // Guardar el libro y, en cascada, los autores
        libroRepositorio.save(libro);
    }
    public Autor obtenerOCrearAutor(String nombreAutor) {
        return autorRepositorio.findByNombre(nombreAutor)
                .orElseGet(() -> {
                    // Si el autor no existe, lo creamos
                    Autor nuevoAutor = new Autor();
                    nuevoAutor.setNombre(nombreAutor);
                    return autorRepositorio.save(nuevoAutor); // Guardamos y retornamos el nuevo autor
                });
    }
}
