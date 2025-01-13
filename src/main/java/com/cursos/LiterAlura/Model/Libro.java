package com.cursos.LiterAlura.Model;

import jakarta.persistence.*;

import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "Libros")
public class Libro
{
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id; // Nombre del campo cambiado a "id" (por convención es mejor usar minúscula)

   @Column(unique = true, nullable = false)
    private String titulo;

    //@ElementCollection
    //@CollectionTable(name = "libro_autores", joinColumns = @JoinColumn(name = "libro_id"))
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "libro_id") // Llave foránea en la tabla de autores
    private List<Autor> autores;
    private List <String> estanteria;
    //@Enumerated(EnumType.STRING)
    private List<String> idiomas;
    private Integer descargas;

   public Libro(){}


    public Libro (DatosLibros datosLibros) {

            //this.id = datosLibros.id() != null ? datosLibros.id().longValue() : null; // Conversión Integer -> Long
            this.titulo = datosLibros.titulo();
            this.autores = datosLibros.autores();
            this.estanteria = datosLibros.estanteria();
            this.idiomas = datosLibros.idiomas();
            this.descargas = datosLibros.descargas();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public List<String> getEstanteria() {
        return estanteria;
    }

    public void setEstanteria(List<String> estanteria) {
        this.estanteria = estanteria;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = Collections.singletonList(idiomas);
    }

    public Integer getDescargas() {
        return descargas;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }

    @Override
    public String toString() {
        return "Título: " + titulo + ", Autor(es): " + (autores != null ? autores : "Desconocido") +
                ", Idioma(s): " + (idiomas != null ? idiomas : "Desconocido") +
                ", Descargas: " + descargas;
    }

    public void setAutor(Autor autor) {
    }
}
