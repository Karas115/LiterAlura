package com.cursos.LiterAlura.dto;

public class AutorDTO {
    private String nombre;      // Nombre del autor
    private Integer nacimiento; // Año de nacimiento del autor
    private Integer muerte;     // Año de muerte del autor

    // Constructor vacío (requerido para frameworks como Jackson)
    public AutorDTO() {}

    // Constructor con todos los campos
    public AutorDTO(String nombre, Integer nacimiento, Integer muerte) {
        this.nombre = nombre;
        this.nacimiento = nacimiento;
        this.muerte = muerte;
    }

    // Getters y Setters
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

    @Override
    public String toString() {
        return "AutorDTO{" +
                "nombre='" + nombre + '\'' +
                ", nacimiento=" + nacimiento +
                ", muerte=" + muerte +
                '}';
    }
}
