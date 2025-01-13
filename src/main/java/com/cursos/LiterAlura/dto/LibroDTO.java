package com.cursos.LiterAlura.dto;

import java.util.List;

public class LibroDTO {

    private String titulo;
    private List<AutorDTO> autores; // Solo nombres de autores
    private List<String> idiomas;
    private List<String> estanteria;
    private Integer descargas;



    public LibroDTO(String titulo, List<AutorDTO> autores, List<String> idiomas, List<String> estanteria, Integer descargas) {
        this.titulo = titulo;
        this.autores = autores;
        this.idiomas = idiomas;
        this.estanteria = estanteria;
        this.descargas = descargas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<AutorDTO> getAutores() {
        return autores;
    }

    public void setAutores(List<AutorDTO> autores) {
        this.autores = autores;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public List<String> getEstanteria() {
        return estanteria;
    }

    public void setEstanteria(List<String> estanteria) {
        this.estanteria = estanteria;
    }

    public Integer getDescargas() {
        return descargas;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }
}
