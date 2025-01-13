package com.cursos.LiterAlura.Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DatosResultados
{

    @JsonProperty("count") private Integer total;
    @JsonProperty("next")private String siguiente;
    @JsonProperty("previous")private String anterior;
    @JsonProperty("results") private  List<DatosLibros> resultados;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(String siguiente) {
        this.siguiente = siguiente;
    }

    public String getAnterior() {
        return anterior;
    }

    public void setAnterior(String anterior) {
        this.anterior = anterior;
    }

    public List<DatosLibros> getResultados() {
        return resultados;
    }

    public void setResultados(List<DatosLibros> resultados) {
        this.resultados = resultados;
    }

    @Override
    public String toString() {
        return "DatosResultados{" +
                "total=" + total +
                ", siguiente='" + siguiente + '\'' +
                ", anterior='" + anterior + '\'' +
                ", resultados=" + resultados +
                '}';
    }

}
