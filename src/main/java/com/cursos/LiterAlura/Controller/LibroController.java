package com.cursos.LiterAlura.Controller;

import com.cursos.LiterAlura.Model.Autor;
import com.cursos.LiterAlura.Model.DatosAutor;
import com.cursos.LiterAlura.Model.Libro;
import com.cursos.LiterAlura.Repository.ILibroRepositorio;
import com.cursos.LiterAlura.Service.LibroService;
import com.cursos.LiterAlura.dto.AutorDTO;
import com.cursos.LiterAlura.dto.LibroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/libros") // Prefijo de las rutas del controlador
public class LibroController {

    @Autowired
    private final LibroService libroService;



    @Autowired
    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }


    @Autowired
    private ILibroRepositorio libroRepositorio;

    @PostMapping
    public void guardarLibro(Libro libro, List<DatosAutor> datosAutores) {
        // Validar entrada
        if (datosAutores == null || datosAutores.isEmpty()) {
            throw new IllegalArgumentException("La lista de autores no puede estar vacía.");
        }

        // Convertir DatosAutor a entidades Autor
        List<Autor> autores = datosAutores.stream()
                .map(datosAutor -> {
                    Autor autor = new Autor();
                    autor.setNombre(datosAutor.nombre());
                    autor.setNacimiento(datosAutor.nacimiento());
                    autor.setMuerte(datosAutor.muerte());
                    autor.setLibro(libro); // Asociar el libro al autor
                    return autor;
                })
                .collect(Collectors.toList());

        // Asociar los autores al libro
        libro.setAutores(autores);

        // Guardar el libro (y, en cascada, los autores)
        libroRepositorio.save(libro);
    }


@GetMapping
    public List<LibroDTO> listarLibros() {
        return libroService.listarLibros(); // Llama al servicio y devuelve los DTO
    }
}

  /*  @GetMapping
    public List<Libro> listarLibros() {
        return libroService.listarLibros(); // Llamada al servicio para obtener libros
    }*/