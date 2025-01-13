package com.cursos.LiterAlura.Model;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

    @Entity
    @Table(name = "autores")
    public class Autor {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;          // Identificador único, posiblemente asignado en la base de datos
        @JsonProperty("name")private String nombre;    // Nombre del autor
        @JsonProperty("birth_year")private Integer nacimiento; // Año de nacimiento
        @JsonProperty("death_year")private Integer muerte;   // Año de muerte

        @ManyToOne
        @JoinColumn(name = "libro_id")
        private Libro libro;

        // Constructor vacío
        public Autor() {}

        // Constructor para inicializar desde DatosAutor
        public Autor(DatosAutor datosAutor) {
            this.nombre = datosAutor.nombre();
            this.nacimiento = datosAutor.nacimiento();
            this.muerte = datosAutor.muerte();
        }

        //Getters y Setters
       public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public Integer getNacimiento() {
            return nacimiento;
        }

        public void setNacimiento(Integer nacimiento) {
            this.nacimiento = nacimiento;
        }

        public Integer getMuerte() {
            return muerte;
        }

        public void setMuerte(Integer muerte) {
            this.muerte = muerte;
        }

        public Libro getLibro() {
            return libro;
        }

        public void setLibro(Libro libro) {
            this.libro = libro;
        }

        @Override
        public String toString() {
            return "Autor{" +

                    ", nombre='" + nombre + '\'' +
                    ", nacimiento=" + nacimiento +
                    ", muerte=" + muerte +
                    '}';

        }
    }

