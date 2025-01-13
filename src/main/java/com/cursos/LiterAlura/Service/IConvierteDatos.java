package com.cursos.LiterAlura.Service;

public interface IConvierteDatos
{
    <T> T obtenerDatos(String json, Class<T> clase);
}
