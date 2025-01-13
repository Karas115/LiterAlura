package com.cursos.LiterAlura.Service;

import com.cursos.LiterAlura.Model.HistorialBusqueda;
import com.cursos.LiterAlura.Repository.IHistorialBusqueda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistorialService {

    @Autowired
    private IHistorialBusqueda historialBusquedaRepositorio;

   /* public void guardarBusqueda(String titulo) {
        historialBusquedaRepositorio.save(new HistorialBusqueda(titulo,));
    }

    public List<HistorialBusqueda> obtenerHistorial() {
        return historialBusquedaRepositorio.findAll();
    }*/
}

