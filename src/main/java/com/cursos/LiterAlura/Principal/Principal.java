package com.cursos.LiterAlura.Principal;

import com.cursos.LiterAlura.Model.*;
import com.cursos.LiterAlura.Repository.IAutorRepositorio;
import com.cursos.LiterAlura.Repository.IHistorialBusqueda;
import com.cursos.LiterAlura.Repository.ILibroRepositorio;
import com.cursos.LiterAlura.Service.ConsultaApi;
import com.cursos.LiterAlura.Service.ConvierteDatos;
import com.cursos.LiterAlura.Service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
@Service
public class Principal
{
    // Lista global para almacenar todas las búsquedas de libros
    private  List<Libro> historialBusqueda = new ArrayList<>();

    private  ILibroRepositorio libroRepository;
    private  IHistorialBusqueda historialBusquedaRepository;
    private IAutorRepositorio autorRepositorio;
    private LibroService libroService;

    public Principal(ILibroRepositorio libroRepository, IHistorialBusqueda historialBusquedaRepository, IAutorRepositorio autorRepositorio, LibroService libroService) {
        this.historialBusquedaRepository = historialBusquedaRepository;
        this.libroRepository=libroRepository;
        this.autorRepositorio= autorRepositorio;
        this.libroService=libroService;
    }


    public void iniciarPrograma() {
        ConvierteDatos convierteDatos = new ConvierteDatos();
        ConsultaApi consultaApi = new ConsultaApi(convierteDatos);
        List<Libro> libros = new ArrayList<>();
        List<Autor> autores = new ArrayList<>();
        Scanner teclado = new Scanner(System.in);
        int opcion = 0;

    String menu = """
                ***************************************
                ***Bienvenido/a a LiterAlura:***
                
                    1- Buscar Libro por Titulo.
                    2- Lista de Autores.
                    3- Lista de Autores vivos en determinado año.
                    4- Total de libros en Inglés.
                    5- Total de libros en Español.
                    6- Lista de libros.
                    7- Top 10 de libros más descargados.
                    8- Salir.
                
                Elija una opción valida:
                ***************************************
                """;
        while (opcion != 8)
    {
        System.out.println(menu);
        opcion = teclado.nextInt();
        teclado.nextLine();

        switch (opcion)
        {
            case 1://Buscar Libro por Titulo
                buscarLibroPorTitulo(consultaApi, teclado);
                break;
            case 2://Lista de Autores
              //  listarAutores();
                break;
            case 3://Lista de Autores vivos en determinado año
               // listarAutoresVivosEnAnio(teclado);
                break;
            case 4://Total de libros en Inglés
                totalLibrosEnIngles();
                break;
            case 5://Total de libros en Español
                totalLibrosEnEspanol();
                break;
            case 6://Lista de libros
                listarLibros();
                break;
            case 7://Top 10 de libros más descargados
                top10LibrosMasDescargados();
                break;
            case 8://Salir
                System.out.println("Saliendo del programa, gracias por utilizar nuestros servicios");
                break;
            default:
                System.out.println("Opción no valida");
        }

    }
}

    private void buscarLibroPorTitulo(ConsultaApi consultaApi, Scanner teclado) {
        System.out.print("Ingrese el título del libro a buscar: ");
        String nombreLibro = teclado.nextLine();

        // Construir la URL para buscar el libro, reemplazando espacios por %20
        String url = "https://gutendex.com/books?search=" + nombreLibro.replace(" ", "%20");

        // Obtener los datos de la API
        DatosResultados datosResultados = consultaApi.obtenerDatos(url);
       // System.out.println(datosResultados);

        if (datosResultados != null && !datosResultados.getResultados().isEmpty()) {
            // Tomar el primer resultado
            DatosLibros primerLibro = datosResultados.getResultados().get(0);

            // Obtener el nombre del primer autor (si existe)
            String nombreAutor = primerLibro.autores() != null && !primerLibro.autores().isEmpty()
                    ? primerLibro.autores().get(0).getNombre()
                    : null;

            // Verificar si ya existe el libro
            if (nombreAutor != null && libroService.existeLibroPorTituloYAutor(primerLibro.titulo(), nombreAutor)) {
                System.out.println("El libro ya existe en la base de datos: " + primerLibro.titulo()
                        + " del autor " + nombreAutor);
                return; // No guarda el libro si ya existe
            }

            // Crear un objeto Libro
            Libro nuevoLibro = new Libro();
            nuevoLibro.setTitulo(primerLibro.titulo());

            // Asociar el primer autor usando el servicio para evitar duplicados
            if (nombreAutor != null) {
                Autor autorExistenteOcreado = libroService.obtenerOCrearAutor(nombreAutor);
                nuevoLibro.setAutores(List.of(autorExistenteOcreado));
            }

            // Tomar solo el primer idioma
            if (primerLibro.idiomas() != null && !primerLibro.idiomas().isEmpty()) {
                nuevoLibro.setIdiomas(List.of(primerLibro.idiomas().get(0)).toString());
            }

            // Guardar el libro en la base de datos
            try {
                libroService.guardarLibro(nuevoLibro);
                System.out.println("Libro guardado exitosamente: " + nuevoLibro);
            } catch (Exception e) {
                System.out.println("Ocurrió un error al guardar el libro: " + e.getMessage());
            }
        } else {
            System.out.println("No se encontró el libro con el título: " + nombreLibro);
        }
    }

    // Método para guardar los libros en la base de datos
    private void guardarLibrosEnBaseDeDatos(List<Libro> libros) {
        try {
            // Guardar los libros en la base de datos
            libroRepository.saveAll(libros);
            System.out.println("Libros guardados en la base de datos.");
        } catch (Exception e) {
            System.out.println("Error al guardar los libros en la base de datos: " + e.getMessage());
        }
    }
    // Método para guardar el historial de búsqueda en la base de datos
    private void guardarEnHistorialDeBusqueda(String nombreLibro) {
        try {
            // Crear una nueva entrada en el historial con el título del libro y la fecha actual
            HistorialBusqueda historial = new HistorialBusqueda(nombreLibro, LocalDateTime.now());

            // Guardar el historial en la base de datos
            historialBusquedaRepository.save(historial);
            System.out.println("Búsqueda registrada en el historial.");
        } catch (Exception e) {
            System.out.println("Error al guardar el historial de búsqueda: " + e.getMessage());
        }
    }

    // 2. Lista de Autores
   /* private void listarAutores() {
        if (historialBusqueda.isEmpty()) {
            System.out.println("No hay búsquedas realizadas.");
            return;
        }

        System.out.println("Autores encontrados en el historial de búsquedas:");
        historialBusqueda.stream()
                .flatMap(libro -> libro.getAutores().stream()) // Extrae todos los autores de cada libro (Lista de DatosAutor)
                .map(DatosAutor -> new Autor(DatosAutor)) // Convierte cada DatosAutor a Autor
                .distinct() // Evita duplicados
                .findFirst() // Obtiene el primer autor de la lista
                .ifPresent(autor -> System.out.println(autor.getNombre())); // Imprime el nombre del primer autor si existe
    }*/


    // 3. Lista de Autores vivos en determinado año
   /* private void listarAutoresVivosEnAnio(Scanner teclado) {
        if (historialBusqueda.isEmpty()) {
            System.out.println("No hay búsquedas realizadas.");
            return;
        }

        System.out.print("Ingrese el año: ");
        int anio = teclado.nextInt();

        System.out.println("Autores vivos en el año " + anio + ":");
        historialBusqueda.stream()
                .flatMap(libro -> libro.getAutores().stream()) // Extrae todos los autores de cada libro
                .map(DatosAutor -> new Autor(DatosAutor)) // Convierte DatosAutor a Autor
                .filter(autor -> {
                    // Validar que nacimiento no sea null y cumplir la condición
                    Integer nacimiento = autor.getNacimiento();
                    Integer muerte = autor.getMuerte();
                    return nacimiento != null && nacimiento <= anio && (muerte == null || muerte > anio);
                })
                .distinct() // Evita duplicados
                .forEach(autor -> System.out.println(autor.getNombre())); // Imprime los nombres de los autores
    }*/

    // 4. Total de libros en Inglés
    private void totalLibrosEnIngles() {
        if (historialBusqueda.isEmpty()) {
            System.out.println("No hay búsquedas realizadas.");
            return;
        }

        long count = historialBusqueda.stream()
                .filter(libro -> libro.getIdiomas().contains("en"))
                .count();

        System.out.println("Total de libros en Inglés: " + count);
    }

    // 5. Total de libros en Español
    private void totalLibrosEnEspanol() {
        if (historialBusqueda.isEmpty()) {
            System.out.println("No hay búsquedas realizadas.");
            return;
        }

        long count = historialBusqueda.stream()
                .filter(libro -> libro.getIdiomas().contains("es"))
                .count();

        System.out.println("Total de libros en Español: " + count);
    }


    // 6. Lista de libros
    private void listarLibros() {
        if (historialBusqueda.isEmpty()) {
            System.out.println("No hay libros disponibles.");
        } else {
            System.out.println("Lista de Libros:");
            for (Libro libro : historialBusqueda) {
                System.out.println(libro);
            }
        }
    }

    // 7. Top 10 de libros más descargados
    private void top10LibrosMasDescargados() {
        historialBusqueda.stream()
                .sorted(Comparator.comparingInt(Libro::getDescargas).reversed())
                .limit(10)
                .forEach(libro -> System.out.println(libro));
    }


    }

