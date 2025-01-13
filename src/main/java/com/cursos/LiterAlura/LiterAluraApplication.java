package com.cursos.LiterAlura;

import com.cursos.LiterAlura.Principal.Principal;
import com.cursos.LiterAlura.Repository.IAutorRepositorio;
import com.cursos.LiterAlura.Repository.IHistorialBusqueda;
import com.cursos.LiterAlura.Repository.ILibroRepositorio;
import com.cursos.LiterAlura.Service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication (scanBasePackages = "com.cursos.LiterAlura")
//@ComponentScan(basePackages = "com.cursos.LiterAlura")
public class LiterAluraApplication implements CommandLineRunner {

	@Autowired
	private ILibroRepositorio libroRepository;
	@Autowired
	private IHistorialBusqueda historialBusquedaRepository;
	@Autowired
	private IAutorRepositorio autorRepositorio;
	@Autowired
	private LibroService libroService;


	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(libroRepository,historialBusquedaRepository,autorRepositorio, libroService);
		principal.iniciarPrograma();
	}
}
