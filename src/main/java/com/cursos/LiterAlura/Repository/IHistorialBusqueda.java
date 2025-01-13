package com.cursos.LiterAlura.Repository;

import com.cursos.LiterAlura.Model.Autor;
import com.cursos.LiterAlura.Model.HistorialBusqueda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHistorialBusqueda extends JpaRepository <HistorialBusqueda, Long> {
}
