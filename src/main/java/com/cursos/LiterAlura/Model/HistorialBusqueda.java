package com.cursos.LiterAlura.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "historial_busqueda")
public class HistorialBusqueda
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo_libro")
    private String tituloLibro;

    @Column(name = "fecha_busqueda")
    private LocalDateTime fechaBusqueda;

    public HistorialBusqueda() {}

    // Constructor, getters y setters
    public HistorialBusqueda(String tituloLibro, LocalDateTime fechaBusqueda) {
        this.tituloLibro = tituloLibro;
        this.fechaBusqueda = fechaBusqueda;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return tituloLibro;
    }

    public void setTitulo(String titulo) {
        this.tituloLibro = titulo;
    }
    public LocalDateTime getFechaBusqueda() {
        return fechaBusqueda;
    }

    public void setFechaBusqueda(LocalDateTime fechaBusqueda) {
        this.fechaBusqueda = fechaBusqueda;
    }
}
